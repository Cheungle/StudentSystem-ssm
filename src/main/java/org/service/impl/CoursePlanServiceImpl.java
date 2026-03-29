package org.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.common.RedisKeyConstant;
import org.dao.CoursePlanDao;
import org.dao.CourseSelectionPermissionDao;
import org.entity.VO.CourseListVO;
import org.entity.VO.RedisCourseListVO;
import org.entity.coursePlan;
import org.handler.exception.PageException;
import org.service.CoursePlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.util.RedisUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class CoursePlanServiceImpl implements CoursePlanService {

    @Autowired
    private CoursePlanDao coursePlanDao;

    @Autowired
    private CourseSelectionPermissionDao courseSelectionPermissionDao;

    @Autowired
    private RedisUtil redisUtil;

    // 缓存页数
    private static final int MAX_CACHE_PAGE = 3;

    @Override
    public boolean isCanSelectCourse(String academicYear, String semester) {
        return courseSelectionPermissionDao.getCourseSelectionPermission(academicYear, semester);
    }

    // 分页查询
    public PageInfo<CourseListVO> findCoursePlanByPage(int pageNum, int pageSize, String academicYear, String semester) {
        /* 分为三部分数据：课程基本信息，库存，总条数 */
        /* 基本查询流程都是：先从redis查，有直接返回，没有就从DB查，然后存回redis */
        /* 查询总数 */
        Integer total = getTotalPlan(academicYear, semester);
        if (pageNum < 1 || pageNum > (int) Math.ceil((double) total / pageSize)) {
            throw new PageException("页码超出范围");
        }
        /* 从redis中查询列表基本信息 */
        boolean isCache = true;
        List<RedisCourseListVO> listFromRedis = redisUtil.getCourseInfo(pageNum, pageSize);
        List<CourseListVO> listFromDB = null;
        List<RedisCourseListVO> listFromDBToRedis = null;
        if (listFromRedis == null) {
            log.info("从redis中查询列表基本信息失败============");
            isCache = false;
            /*从数据库中查列表,包含库存*/
            PageHelper.startPage(pageNum, pageSize, false);
            listFromDB = coursePlanDao.findAllCoursePlan(academicYear, semester);
            /*查回的list去掉库存*/
            listFromDBToRedis = listFromDB.stream().map(item -> {
                RedisCourseListVO vo = new RedisCourseListVO();
                BeanUtils.copyProperties(item, vo);
                return vo;
            }).collect(Collectors.toList());
            /*缓存前3页列表数据*/
            if (pageNum <= MAX_CACHE_PAGE && pageNum >= 1) {
                log.info("缓存列表");
                redisUtil.setCourseInfo(pageNum, pageSize, listFromDBToRedis);
            }
        }

        /* 查库存 */
        List<RedisCourseListVO> lastList = isCache ? listFromRedis : listFromDBToRedis;
        /* 取出id list */
        List<Integer> idList = lastList.stream().map(RedisCourseListVO::getIdPlan).collect(Collectors.toList());
        log.info("========课程ids=========="+idList);
        /* 从redis中批量查库存 查不到的置为-1 */
        List<Integer> stockListFromRedis = redisUtil.batchGetCourseStock(idList);
        // 找出Redis中不存在的ID
        List<Integer> needLoadIds = new ArrayList<>();
        for (int i = 0; i < stockListFromRedis.size(); i++) {
            if (stockListFromRedis.get(i) == -1) {
                needLoadIds.add(idList.get(i));
            }
        }
        /* redis部分库存查不到，需要从DB查 */
        if (!needLoadIds.isEmpty()) {
            if (!isCache) {
                // list是从DB查回来时,不用重复查库存
                // 用DB查回来的库存补充到redis中，不覆盖redis已有的
                for (int i = 0; i < stockListFromRedis.size(); i++) {
                    if (stockListFromRedis.get(i) == -1) {
                        stockListFromRedis.set(i, listFromDB.get(i).getRemainderStock());
                    }
                }
            } else {
                // list是从redis中直接取的，需从DB查库存
                List<Integer> stockListFromDB = coursePlanDao.findCoursePlanStockBetween(academicYear, semester, needLoadIds);
                // 用DB查回来的库存补充到redis中，不覆盖redis已有的
                for (int i = 0, j = 0; i < stockListFromRedis.size(); i++) {
                    if (stockListFromRedis.get(i) == -1) {
                        stockListFromRedis.set(i, stockListFromDB.get(j));
                        j++;
                    }
                }
            }
            redisUtil.batchSetCourseStock(idList, stockListFromRedis);
        }
        log.info("开始拼接列表显示数据==============");
        // 拼接最终的列表和库存，手动构建分页
        List<CourseListVO> resultShowList = IntStream.range(0, idList.size())
                .mapToObj(i -> {
                    CourseListVO courseListVO = new CourseListVO();
                    BeanUtils.copyProperties(lastList.get(i), courseListVO);
                    courseListVO.setRemainderStock(stockListFromRedis.get(i));
                    return courseListVO;
                })
                .collect(java.util.stream.Collectors.toList());


        return buildPageInfo(pageNum, pageSize, resultShowList, total);
    }

    @Override
    public boolean addCourse(coursePlan coursePlan) {
        return false;
    }

    @Override
    public boolean updateCourse(coursePlan coursePlan) {
        return false;
    }

    @Override
    public boolean deleteCourse(int idPlan) {
        return false;
    }

    @Override
    public int countCourse() {
        return 0;
    }

    @Override
    public List<coursePlan> queryCourseByTeacherId(int id) {
        return Collections.emptyList();
    }

    /* 获取course plan的总数 */
    public Integer getTotalPlan(String academicYear, String semester) {
        Integer total = redisUtil.getTotalPlan();
        if (total == null) {
            // 缓存没有 → 查数据库
            total = coursePlanDao.countTotalPlan(academicYear, semester);
            // 存入Redis
            redisUtil.setTotalPlan(total);
        }
        return total;
    }

    /* 构建分页 */
    public <E> PageInfo<E> buildPageInfo(int pageNum, int pageSize, List<E> list, int total) {

        PageInfo<E> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(total);
        pageInfo.setPages((int) Math.ceil((double) total / pageSize));
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);

        int pages = pageInfo.getPages();
        pageInfo.setIsFirstPage(pageNum == 1);
        pageInfo.setIsLastPage(pageNum == pages);
        pageInfo.setPrePage(pageNum > 1 ? pageNum - 1 : 0);
        pageInfo.setNextPage(pageNum < pages ? pageNum + 1 : 0);
        pageInfo.setHasPreviousPage(pageNum > 1);
        pageInfo.setHasNextPage(pageNum < pages);
        return pageInfo;
    }


}
