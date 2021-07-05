package com.learn.springboot.practice.mq;

/**
 * 基于Redis实现的简易MQ
 */
public interface MessagePublisher {
    void publish(String message);
}
