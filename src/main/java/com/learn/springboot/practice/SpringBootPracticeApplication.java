package com.learn.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author lfq
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@Slf4j
public class SpringBootPracticeApplication {
    public static void main(String[] args) {
        log.info("SpringBootPracticeApplication start");
        SpringApplication.run(SpringBootPracticeApplication.class, args);
        log.info("SpringBootPracticeApplication finish");
    }
}
