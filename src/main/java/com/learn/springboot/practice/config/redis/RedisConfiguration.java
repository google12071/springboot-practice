package com.learn.springboot.practice.config.redis;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis工具类
 *
 * @author lfq
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisManager redisManager(@Autowired RedissonClient redissonClient) {
        return new RedissonRedisManager(redissonClient);
    }
}
