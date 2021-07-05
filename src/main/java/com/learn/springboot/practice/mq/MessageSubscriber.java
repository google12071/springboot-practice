package com.learn.springboot.practice.mq;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MessageSubscriber
 * @Description:消息订阅者
 * @Author lfq
 * @Date 2021/3/22
 **/
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Component
public class MessageSubscriber implements MessageListener {

    private List<String> messageList;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("message:{}", new String(message.getBody(),"utf-8"));
        messageList.add("[pattern:" + new String(pattern) + ",message:" + message.toString() + "]");
    }
}
