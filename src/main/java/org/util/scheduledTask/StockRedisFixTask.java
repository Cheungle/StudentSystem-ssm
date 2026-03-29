package org.util.scheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.dao.CoursePlanDao;
import org.dao.ScDao;
import org.entity.DTO.TermDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.util.RedisUtil;

import java.util.List;
import java.util.Set;

import static org.common.RedisKeyConstant.COURSE_SELECTED_STUDENT;

@Component
@Slf4j
public class StockRedisFixTask {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ScDao scDao;
    @Autowired
    private CoursePlanDao coursePlanDao;

    /**
     * 每天凌晨 2:00 执行
     * 1. 对账修复 Redis 库存
     * 2. 同步正确库存到 MySQL
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void checkAndSyncStock() {
        // 非选课期间直接退出，不执行定时任务
        TermDTO termDTO = coursePlanDao.getTermWithOpenSelection();
        if (termDTO == null) {
            log.info("当前非选课时间段，库存定时同步已关闭");
            return;
        }
        log.info("===== 开始凌晨库存对账 + 同步 =====");

        // 获取本学期开放课程Id
        List<Integer> courseIdList =
                coursePlanDao.findThisTermValidCourseIds(termDTO.getAcademicYear(), termDTO.getSemester());

        for (Integer courseId : courseIdList) {
            try {
                // 通过DB选课记录计算真实库存
                int capacity = coursePlanDao.getTotalStockById(courseId);
                int selectedStudent = scDao.countByCourseId(courseId);
                int realStock = Math.max(capacity - selectedStudent, 0);

                Integer redisStock = redisUtil.getCurrentStock(courseId);

                // Redis 库存不存在 → 未加载，忽略
                if (redisStock != null) {
                    // 强制对齐
                    log.warn("课程{} Redis={} 真实={} → 自动修复",
                            courseId, redisStock, realStock);
                    redisUtil.setStock(courseId, realStock);
                    // 重建 redis 选课记录
                    Set<Integer> selectedStudentIds = scDao.getSelectedStudentsByCourseId(courseId);
                    redisUtil.delete(COURSE_SELECTED_STUDENT + "{" + courseId + "}");
                    if(!selectedStudentIds.isEmpty()){
                        redisUtil.batchAddCourseSelectedRecord(courseId, selectedStudentIds);
                    }

                }
            } catch (Exception e) {
                log.error("课程{} 库存对账异常", courseId, e);
            }
        }
        log.info("===== 凌晨库存对账 + 同步完成 =====");
    }
}
