package org.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:mq.properties")
@EnableRabbit
public class MQConfig {

    public static final String COURSE_QUEUE = "course.select.queue";
    public static final String COURSE_EXCHANGE = "course.select.exchange";
    public static final String ROUTING_KEY = "course.select";
    public static final String DLX_QUEUE = "dlx.queue";


    @Value("${mq.host}")
    private String host;
    @Value("${mq.port}")
    private int port;
    @Value("${mq.username}")
    private String username;
    @Value("${mq.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(host,port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(jackson2JsonMessageConverter());

        // 保证生产者端消息不丢失
        // 开启Confirm确认（交换机收到消息回调）
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                // 成功抵达交换机
                System.out.println("消息发送成功抵达交换机");
            } else {
                // 丢失告警，可落地日志/重试
                System.err.println("消息发送失败，未抵达交换机：" + cause);
            }
        });
        // 开启Return回调（路由不到队列回调）
        template.setMandatory(true);
        template.setReturnsCallback(returned -> {
            System.err.println("消息路由失败，无法投递队列：" + returned.getMessage());
        });
        return template;
    }

    // 消息转换器 JSON
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue courseQueue() {
        Map<String, Object> args = new HashMap<>();
        // 配置死信交换机和队列
        args.put("x-dead-letter-exchange", "dlx.exchange");
        args.put("x-dead-letter-routing-key", "dlx.routing.key");
        return new Queue(COURSE_QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange courseExchange() {
        return new DirectExchange(COURSE_EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(courseQueue()).to(courseExchange()).with(ROUTING_KEY);
    }

    /* 死信队列 */
    @Bean
    public Queue dlxQueue() {
        return new Queue(DLX_QUEUE, true);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx.exchange", true, false);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlx.routing.key");
    }

    // 监听容器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(CachingConnectionFactory factory) {
        SimpleRabbitListenerContainerFactory bean = new SimpleRabbitListenerContainerFactory();
        bean.setConnectionFactory(factory);
        bean.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return bean;
    }
}
