package org.util.mq;

import org.config.MQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MQProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSelectMsg(int studentId, int courseId) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("idStudent", studentId);
        msg.put("idPlan", courseId);
        rabbitTemplate.convertAndSend(MQConfig.COURSE_EXCHANGE, MQConfig.ROUTING_KEY, msg, message -> {
            // 设置消息持久化：deliveryMode=2
            MessageProperties props = message.getMessageProperties();
            props.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }
}
