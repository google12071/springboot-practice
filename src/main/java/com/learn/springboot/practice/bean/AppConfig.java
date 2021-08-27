package com.learn.springboot.practice.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.ZoneId;

/**
 * 条件装配
 *
 * @author lfq
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    @Profile("!test")
    ZoneId createZonId() {
        return ZoneId.systemDefault();
    }


    @Bean
    @Profile("test")
    ZoneId createZoneIdForTest() {
        return ZoneId.of("America/New_York");
    }
}
