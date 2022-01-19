package com.learn.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lfq
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableRetry
@EnableScheduling
@Slf4j
public class SpringBootPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPracticeApplication.class, args);
    }
}
