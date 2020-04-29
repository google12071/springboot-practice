package com.learn.springboot.practice.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName CustomizeFilterConfig
 * @Description:自定义过滤器配置
 * @Author lfq
 * @Date 2020/4/29
 **/
@Configuration
public class CustomizeFilterConfig {
    @Autowired
    private FirstFilter firstFilter;

    @Autowired
    private SecondFilter secondFilter;

    @Bean
    public FilterRegistrationBean<FirstFilter> setFirstFilter() {
        FilterRegistrationBean<FirstFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(firstFilter);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> setSecondFilter() {
        FilterRegistrationBean<SecondFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(secondFilter);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }
}
