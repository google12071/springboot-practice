package com.learn.springboot.practice.config.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebInterceptorConfig
 * @Description:Web拦截器配置
 * @Author lfq
 * @Date 2020/4/29
 **/
@Slf4j
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }

    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }
}
