package org.util;

import lombok.extern.slf4j.Slf4j;
import org.entity.VO.CourseListVO;
import org.entity.VO.RedisCourseListVO;
import org.entity.VO.StudentInfoVO;
import org.entity.coursePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.common.RedisKeyConstant.*;

@Component
@Slf4j
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteAll() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean isHashEmpty(String key) {
        return redisTemplate.opsForHash().size(key) == 0 || redisTemplate.opsForHash().size(key) == null;
    }

    public void setUserInfo(int id, StudentInfoVO studentInfoVO) {
        redisTemplate.opsForHash().putAll(USER_INFO + id, studentInfoVO.toMap());
        redisTemplate.expire(USER_INFO + id, 10, TimeUnit.MINUTES);
    }

    public Map<Object, Object> getUserInfo(int id) {
        return redisTemplate.opsForHash().entries("info:" + id);
    }

    /*存入选课列表总数*/
    public void setTotalPlan(int total) {
        redisTemplate.opsForValue().set(COURSE_LIST_TOTAL, total, 20, TimeUnit.MINUTES);
    }

    public Integer getTotalPlan() {
        return (Integer) redisTemplate.opsForValue().get(COURSE_LIST_TOTAL);
    }


    /*存入课程基本信息*/
    public void setCourseInfo(int pageNum, int pageSize, List<RedisCourseListVO> courseInfo) {
        redisTemplate.opsForValue().set(COURSE_LIST_BASE + pageNum + "-" + pageSize, courseInfo, 10, TimeUnit.MINUTES);
    }

    public List<RedisCourseListVO> getCourseInfo(int pageNum, int pageSize) {
        return (List<RedisCourseListVO>) redisTemplate.opsForValue().get(COURSE_LIST_BASE + pageNum + "-" + pageSize);
    }

    /*批量存入课程库存*/
    public void batchSetCourseStock(List<Integer> courseIds, List<Integer> stockList) {
        Map<String, Integer> map = IntStream.range(0, courseIds.size())
                .boxed()
                .collect(Collectors.toMap(i -> COURSE_STOCK + "{" + courseIds.get(i) + "}", stockList::get));
        redisTemplate.opsForValue().multiSet(map);
    }

    public List<Integer> batchGetCourseStock(List<Integer> courseIds) {
        List<String> keys = courseIds.stream()
                .map(id -> COURSE_STOCK + "{" + id + "}")
                .collect(Collectors.toList());
        List<Object> objList = redisTemplate.opsForValue().multiGet(keys);
        List<Integer> stockList = new ArrayList<>();
        for (Object obj : objList) {
            if (obj == null) {
                stockList.add(-1); // 没有库存默认-1
            } else {
                stockList.add((Integer) obj); // 单个强转
            }
        }
        return stockList;
    }

    /*判断该学生是否已经选过该课程*/
    public boolean isSelectedThisCourse(int courseId, int studentId) {
        Boolean isMember = redisTemplate.opsForSet().isMember(COURSE_SELECTED_STUDENT + "{" + courseId + "}", studentId);
        if (isMember == null) {
            // Redis 异常时返回 null，让调用方决定如何处理
            throw new RuntimeException("Redis 连接异常，无法判断选课状态");
        }
        return isMember;
    }

    /*添加选课记录*/
    public void batchAddCourseSelectedRecord(int courseId, Set<Integer> studentIds) {
        redisTemplate.opsForSet().add(COURSE_SELECTED_STUDENT + "{" + courseId + "}", studentIds.toArray());
    }

    public void addCourseSelectedRecord(int courseId, int studentId) {
        redisTemplate.opsForSet().add(COURSE_SELECTED_STUDENT + "{" + courseId + "}", studentId);
    }

    /*高并发选课 扣库存*/
    public int selectCourse(int courseId, int studentId) {
        log.info("========redis开始扣库存========");
        String stockKey = COURSE_STOCK + "{" + courseId + "}";
        String selectedSetKey = COURSE_SELECTED_STUDENT + "{" + courseId + "}";
        String lua =
                // 判断该学生是否已经选过该课程
                "if redis.call('SISMEMBER', KEYS[2], ARGV[1]) == 1 then \n" +
                        "   return 1 \n" +
                        "end\n" +
                        // 判断库存是否足够
                        "local stock = tonumber(redis.call('GET', KEYS[1]) or 0)\n " +
                        "if stock <= 0 then \n" +
                        "   return 2 \n" +
                        "end\n" +
                        // 扣库存，增加选课记录
                        "redis.call('DECR', KEYS[1])\n " +
                        "redis.call('SADD', KEYS[2], ARGV[1]) \n" +
                        "return 0\n";
        // 执行脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(lua);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Arrays.asList(stockKey, selectedSetKey), // KEYS1, KEYS2
                studentId);
        return result.intValue();
    }

    public int cancelCourse(int courseId, int studentId) {
        log.info("========redis开始加库存========");
        String stockKey = COURSE_STOCK + "{" + courseId + "}";
        String selectedSetKey = COURSE_SELECTED_STUDENT + "{" + courseId + "}";
        String lua =
                // 判断该学生是否已经选过该课程
                "if redis.call('SISMEMBER', KEYS[2], ARGV[1]) ~= 1 then \n" +
                        "   return 1 \n" +
                        "end\n" +
                        // 加库存，删除选课记录
                        "redis.call('INCR', KEYS[1])\n " +
                        "redis.call('SREM', KEYS[2], ARGV[1]) \n" +
                        "return 0\n";
        // 执行脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(lua);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Arrays.asList(stockKey, selectedSetKey), // KEYS1, KEYS2
                studentId);
        return result.intValue();
    }

    public Integer getCurrentStock(int courseId) {
        return (Integer) redisTemplate.opsForValue().get(COURSE_STOCK + "{" + courseId + "}");
    }

    public void setStock(int courseId, int stock) {
        redisTemplate.opsForValue().set(COURSE_STOCK + "{" + courseId + "}", stock);
    }

    /* 获取所有stock */
    public Map<Integer, Integer> getAllStockWithCourseId(List<Integer> Ids) {
        Map<Integer, Integer> map = new HashMap<>();
        List<String> keys = Ids.stream()
                .map(id -> COURSE_STOCK + "{" + id + "}")
                .collect(Collectors.toList());
        List<Object> objList = redisTemplate.opsForValue().multiGet(keys);
        List<Object> stockList = new ArrayList<>();
        for (Object obj : objList) {
            if (obj == null) {
                stockList.add(-1); // 没有库存默认-1
            } else {
                stockList.add((Integer) obj); // 单个强转
            }
        }
        for (int i = 0; i < Ids.size(); i++) {
            map.put(Ids.get(i), (Integer) stockList.get(i));
        }
        return map;
    }

    /* 设置异步选课结果 */
    public void setSelectResult(int courseId, int studentId, boolean result, String msg) {
        String res = result ? "success" : "fail";
        redisTemplate.opsForValue().set(COURSE_SELECTED_RESULT + courseId + ":" + studentId, res + ":" + msg, 5, TimeUnit.MINUTES);

    }

    public String getSelectResult(int courseId, int studentId) {
        return (String) redisTemplate.opsForValue().get(COURSE_SELECTED_RESULT + courseId + ":" + studentId);
    }
}
