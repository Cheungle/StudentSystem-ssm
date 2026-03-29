package org.util.scheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.dao.CoursePlanDao;
import org.entity.DTO.TermDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.util.RedisUtil;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class StockSyncToDBTask {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CoursePlanDao coursePlanDao;

    /**
     * 定时同步 Redis 库存 → DB
     * 固定 30 秒执行一次
     */
    @Scheduled(fixedRate = 30000)
    public void syncRedisStockToDB() {
        log.info("=======定时同步库存开始========");
        // 非选课期间直接退出，不执行定时任务
        TermDTO termDTO= coursePlanDao.getTermWithOpenSelection();
        if(termDTO == null){
            log.info("当前非选课时间段，库存定时同步已关闭");
            return;
        }
        // 从DB获取所有有效的课程 ID 再从redis获取对应的 库存
        List<Integer> courseIdList =
                coursePlanDao.findThisTermValidCourseIds(termDTO.getAcademicYear(), termDTO.getSemester());
        if(courseIdList.isEmpty()) {
            log.info("无有效课程，库存定时同步已关闭");
            return;
        }

        Map<Integer,Integer> stockMap= redisUtil.getAllStockWithCourseId(courseIdList);

        for (Integer courseId : courseIdList) {
            int stock = stockMap.get(courseId);
            if (stock == -1) {
                continue; // redis库存不存在，跳过
            }
            // 库存直接覆盖更新到数据库
            coursePlanDao.updateStock(courseId, stock);
        }
        log.info("定时同步库存完成");
    }
}
