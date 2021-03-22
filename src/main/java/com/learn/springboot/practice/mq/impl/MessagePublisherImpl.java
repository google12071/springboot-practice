package com.learn.springboot.practice.mq.impl;

import com.learn.springboot.practice.mq.MessagePublisher;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import javax.annotation.Resource;

/**
 * @ClassName MessagePublisherIm
 * @Description: 消息发布者
 * @Author lfq
 * @Date 2021/3/22
 **/
@Slf4j
@Setter
public class MessagePublisherImpl implements MessagePublisher {
    @Resource(name = "genericJackson2Template")
    private RedisTemplate<String, Object> redisTemplate;

    private ChannelTopic topic;

    public MessagePublisherImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
