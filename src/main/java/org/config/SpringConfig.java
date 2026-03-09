package org.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan({"org.service","org.dao"})
@Import({JDBCConfig.class,MyBatisConfig.class})
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
}
