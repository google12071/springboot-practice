package com.learn.springboot.practice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description:Mybatis多数据源配置
 * @Author lfq
 * @Date 2020/4/22
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "mybatisDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mybatis")
    public DataSource myBatisDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "learnDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.learn")
    public DataSource learnDataSource() {
        return DataSourceBuilder.create().build();
    }
}
