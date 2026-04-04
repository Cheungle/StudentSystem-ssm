package org.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.common.RedisKeyConstant;
import org.common.Result;
import org.dao.CoursePlanDao;
import org.dao.ScDao;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.GradeInfoVO;
import org.entity.VO.TestInfoVO;
import org.entity.VO.TimetableVO;
import org.entity.courseChoose;
import org.handler.exception.LuaException;
import org.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.util.RedisUtil;
import org.util.mq.MQProducer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScServiceImpl implements ScService {
    @Autowired
    private ScDao scDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private CoursePlanDao coursePlanDao;
//    @Autowired
//    private RedissonClient redissonClient;

    public List<TimetableVO> getTimetableOfTermByStudent(QueryStudentDTO queryStudentDTO) {
        return scDao.getTimetableOfTermByStudent(queryStudentDTO);
    }

    @Override
    public List<courseChoose> getGradeOfOne(QueryStudentDTO queryStudentDTO) {
        return null;
    }

    @Override
    public List<courseChoose> getGradeByCourseID(int id) {
        return null;
    }

    public double getAverageGPA() {
        return 0.0;
    }

    @Override
    public int countCourseOfOne(QueryStudentDTO queryStudentDTO) {
        return scDao.countCourseOfOne(queryStudentDTO);
    }

    @Override
    public int countCreditOfTerm(QueryStudentDTO queryStudentDTO) {
        return scDao.countCreditOfTerm(queryStudentDTO);
    }

    @Override
    public int countBeforeCreditOfOne(QueryStudentDTO queryStudentDTO) {
        return scDao.countBeforeCreditOfOne(queryStudentDTO);
    }

    @Override
    public List<TimetableVO> getTodayCourse(QueryStudentDTO queryStudentDTO) {
        return scDao.getTodayCourse(queryStudentDTO);
    }

    //    @Override
//    @Async("corePool")
//    public void selectOneCourse(int idPlan, int idStudent) {
//        // 分布式锁
//        String lockKey = RedisKeyConstant.COURSE_SELECT_LOCK + idPlan + idStudent;
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            boolean tryLock = lock.tryLock(0, 3, TimeUnit.SECONDS);
//            if (!tryLock) {
//                redisUtil.setSelectResult(idPlan, idStudent, false,"请勿重复提交");
//            }
//            // 先判断该学生是否已选过该课程
//            if (checkIfSelected(idPlan, idStudent)) {
//                log.info("学生{}已选{}课程", idStudent, idPlan);
//                redisUtil.setSelectResult(idPlan, idStudent, false,"请勿重复选课");
//            }
//            // lua判断库存是否足够
//            boolean redisSuccess = redisUtil.deductStockAtomic(idPlan,idStudent);
//            if (!redisSuccess) {
//                log.info("课程{}库存不足", idPlan);
//                redisUtil.setSelectResult(idPlan, idStudent, false,"课程库存不足");
//            }
//            // 选课成功，将该学生加入已选set
//            redisUtil.addCourseSelectedRecord(idPlan, idStudent);
//            // 异步同步库存到数据库
//            mqProducer.sendSelectMsg(idStudent, idPlan);
//            redisUtil.setSelectResult(idPlan, idStudent, true,"");
//        }catch (Exception e) {
//            redisUtil.setSelectResult(idPlan, idStudent, false,"系统异常，"+e.getMessage());
//        } finally {
//            if (lock.isHeldByCurrentThread()) {
//                lock.unlock();
//            }
//        }
//    }
    @Override
    @Async("corePool")
    public void selectOneCourse(int idPlan, int idStudent) {
        try {
            // lua判断库存是否足够
            int redisSuccess = redisUtil.selectCourse(idPlan, idStudent);
            switch (redisSuccess){
                case 0:
                    log.info("学生{}已选{}课程", idStudent, idPlan);
                    // 异步同步库存到数据库
                    mqProducer.sendSelectMsg(idStudent, idPlan);
                    redisUtil.setSelectResult(idPlan, idStudent, true, "选课成功");
                    break;
                case 1:
                    log.info("学生{}已选{}课程", idStudent, idPlan);
                    redisUtil.setSelectResult(idPlan, idStudent, false, "请勿重复选课");
                    break;
                case 2:
                    log.info("课程{}库存不足", idPlan);
                    redisUtil.setSelectResult(idPlan, idStudent, false, "课程库存不足");
                    break;
                default:
                    throw new LuaException("选课Lua脚本异常");
            }
        } catch (Exception e) {
            redisUtil.setSelectResult(idPlan, idStudent, false, "系统异常，" + e.getMessage());
        }
    }

    @Override
    public Result cancelOneCourse(int idPlan, int idStudent) {
        int redisSuccess = redisUtil.cancelCourse(idPlan, idStudent);
        switch (redisSuccess){
            case 0:
                // 同步到数据库
                cancelDataToDB(idPlan, idStudent);
                return Result.success("退课成功");
            case 1:
                return Result.fail("学生未选过该课程");
            default:
                throw new LuaException("退课Lua脚本异常");
        }
    }
public void cancelDataToDB(int idPlan, int idStudent) {
    // 删除选课记录
    scDao.cancelCourseSelection(idPlan,idStudent);
    // 增加库存
    coursePlanDao.addStock(idPlan);
}
//    public boolean checkIfSelected(int courseId, int studentId) {
//        String selectedKey = RedisKeyConstant.COURSE_SELECTED_STUDENT + "{" + courseId + "}";
//        // 看 Redis 有没有这个选课集合
//        boolean hasKey = redisUtil.hasKey(selectedKey);
//        // 如果 Redis 没有 → 从数据库加载
//        if (!hasKey) {
//            // 从数据库查该课程的所有已选学生ID
//            Set<Integer> selectedStudentIds = scDao.getSelectedStudentsByCourseId(courseId);
//            if (selectedStudentIds != null && !selectedStudentIds.isEmpty()) {
//                // 批量存入 Redis Set
//                redisUtil.batchAddCourseSelectedRecord(courseId, selectedStudentIds);
//            }
//        }
//        // 判断是否重复
//        return redisUtil.isSelectedThisCourse(courseId, studentId);
//    }

    @Override
    public Result getSelectResult(int idPlan, int idStudent) {
        Result res = new Result();
        String result = redisUtil.getSelectResult(idPlan, idStudent);
        if (result == null) {
            res.setCode(100);
            return res;
        }
        String[] split = result.split(":");
        if (split[0].equals("success")) {
            res.setCode(200);
            res.setMsg(split[1]);
        }
        if (split[0].equals("fail")) {
            res.setCode(500);
            res.setMsg(split[1]);
        }
        return res;
    }

    @Override
    public List<GradeInfoVO> getCoursesGrade(QueryStudentDTO queryStudentDTO) {
        return scDao.getCoursesGrade(queryStudentDTO);
    }

    @Override
    public Map<String, List<GradeInfoVO>> getCoursesPastGrade(QueryStudentDTO queryStudentDTO) {
        List<GradeInfoVO> testInfoVOS = scDao.getCoursesPastGrade(queryStudentDTO);
        Map<String, List<GradeInfoVO>> groupMap = testInfoVOS.stream()
                .collect(Collectors.groupingBy(vo ->
                        vo.getAcademicYear() + " " + vo.getSemester()
                ));
        return groupMap;
    }
}
