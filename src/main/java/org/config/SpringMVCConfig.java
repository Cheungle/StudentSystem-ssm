package org.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.util.JWTUtil;

@Configuration
@ComponentScan("org.controller,org.interception")
@Import({SpringMVCSupportConfig.class, JWTUtil.class})
@PropertySource("classpath:jwt.properties")
public class SpringMVCConfig {


}
