package org.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JDBCConfig {
    @Value("${jdbc.url}")
    private String JDBC_URL;
    @Value("${jdbc.username}")
    private String JDBC_USER;
    @Value("${jdbc.password}")
    private String JDBC_PASSWORD;
    @Value("${jdbc.driver}")
    private String JDBC_DRIVER;
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USER);
        dataSource.setPassword(JDBC_PASSWORD);
        dataSource.setDriverClassName(JDBC_DRIVER);
        return dataSource;
    };
}
