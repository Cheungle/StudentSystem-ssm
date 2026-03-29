package org.config;

import lombok.extern.slf4j.Slf4j;
import org.interception.LoginInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Slf4j
public class SpringMVCSupportConfig extends WebMvcConfigurationSupport {
    @Autowired
    private LoginInterception loginInterception;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/vendor/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/data/**").addResourceLocations("/data/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(loginInterception)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/adminindex","/index",
                        "/css/**", "/js/**", "/img/**", "/vendor/**", "/fonts/**", "/data/**");
    }
}
