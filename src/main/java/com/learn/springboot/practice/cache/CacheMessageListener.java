package com.learn.springboot.practice.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @ClassName CacheMessageListener
 * @Description:
 * @Author lfq
 * @Date 2020/4/20
 **/
@Slf4j
public class CacheMessageListener implements MessageListener {
    private RedisTemplate<String, Object> redisTemplate;

    private RedisCaffeineCacheManager redisCaffeineCacheManager;

    public CacheMessageListener(RedisCaffeineCacheManager redisCaffeineCacheManager,RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisCaffeineCacheManager = redisCaffeineCacheManager;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        CacheMessage cacheMessage = (CacheMessage) redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.debug("receive a redis topic message, clear local cache, the cacheName is {}, the key is {}", cacheMessage.getCacheName(), cacheMessage.getKey());
        redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey());
    }
}
