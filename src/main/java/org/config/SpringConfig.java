package org.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.util.RedisUtil;
import org.util.mq.MQConsumer;
import org.util.mq.MQProducer;

import javax.sql.DataSource;


@Configuration
@ComponentScan({"org.service","org.dao","org.util.mq","org.util.scheduledTask"})
@Import({JDBCConfig.class,MyBatisConfig.class,RedisConfig.class, RedisUtil.class, ThreadPoolConfig.class, MQConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class SpringConfig {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
