package org.util.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.dao.CoursePlanDao;
import org.dao.ScDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

import static org.config.MQConfig.COURSE_QUEUE;
import static org.config.MQConfig.DLX_QUEUE;

@Component
@Slf4j
public class MQConsumer {
    @Autowired
    private ScDao scDao;
    @Autowired
    private CoursePlanDao coursePlanDao;

    @RabbitListener(queues = COURSE_QUEUE)
    public void consume(String json, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info("开始消费");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> msg = mapper.readValue(json, Map.class);

        int idPlan = (int) msg.get("idPlan");
        int idStudent = (int) msg.get("idStudent");
        try {
            // 插入选课记录
            scDao.addSelectRecord(idPlan, idStudent);
            log.info("MQ异步同步DB成功：课程{} 学生{}", idPlan, idStudent);
            channel.basicAck(tag, false);
        } catch (DuplicateKeyException e) {
            // 标记为重复选课
            msg.put("exceptionType", "DuplicateKeyException");
            channel.basicNack(tag, false, false);
        } catch (Exception e) {
            log.error("消费失败，进入死信队列：{}", msg, e);
            channel.basicNack(tag, false, false);
        }

    }

    @RabbitListener(queues = DLX_QUEUE)
    public void consumeDLX(Map<String, Object> msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        // 判断是否是重复选课（可以从消息头或业务逻辑判断）
        String exceptionType = (String) msg.get("exceptionType");
        if ("DuplicateKeyException".equals(exceptionType)) {
            // 重复选课 - 确认后直接丢弃
            log.warn("死信队列收到重复选课，丢弃：{}", msg);
            // 修正
            channel.basicAck(tag, false);
        } else {
            // 其他错误 - 记录日志并告警
            log.error("死信队列收到异常消息，需人工介入：{}", msg);
            // 可以存入数据库待人工处理
            channel.basicAck(tag, false);
        }
    }

}
