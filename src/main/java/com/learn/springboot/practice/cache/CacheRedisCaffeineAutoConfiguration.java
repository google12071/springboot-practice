package com.learn.springboot.practice.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName CacheRedisCaffeineAutoConfiguration
 * @Description:
 * @Author lfq
 * @Date 2020/4/20
 **/
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
public class CacheRedisCaffeineAutoConfiguration {
    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Resource(name = "genericJackson2Template")
    private RedisTemplate<String, Object> redisTemplate;

    @Bean(name = "redisCaffeineCacheManager")
    public RedisCaffeineCacheManager cacheManager() {
        return new RedisCaffeineCacheManager(cacheRedisCaffeineProperties);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        CacheMessageListener cacheMessageListener = new CacheMessageListener(cacheManager(), redisTemplate);
        redisMessageListenerContainer.addMessageListener(cacheMessageListener, new ChannelTopic(cacheRedisCaffeineProperties.getRedis().getTopic()));
        return redisMessageListenerContainer;
    }
}

