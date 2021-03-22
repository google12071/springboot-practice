package com.learn.springboot.practice.redis;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.mq.MessagePublisher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName RedisPubSubTest
 * @Description:
 * @Author lfq
 * @Date 2021/3/22
 **/

@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@Data
public class RedisPubSubTest {
    @Autowired
    @Qualifier("redisPublisherForTopic1")
    private MessagePublisher redisPublisher1;

    @Autowired
    @Qualifier("redisPublisherForTopic2")
    private MessagePublisher redisPublisher2;

    @Autowired
    @Qualifier("messageListener1")
    private MessageListener subscriber1;

    @Autowired
    @Qualifier("messageListener2")
    private MessageListener subscriber2;

    @Test
    public void pubSub(){
        // 循环发布10次消息, 主要方法 redisTemplate.convertAndSend
        for (int i = 0; i < 10; i++) {
            String message = "Topic1 Message : " + UUID.randomUUID();
            redisPublisher1.publish(message);
        }

        // 循环发布10次消息, 主要方法 redisTemplate.convertAndSend
        for (int i = 0; i < 10; i++) {
            String message = "Topic2 Message : " + UUID.randomUUID();
            redisPublisher2.publish(message);
        }
    }

}
