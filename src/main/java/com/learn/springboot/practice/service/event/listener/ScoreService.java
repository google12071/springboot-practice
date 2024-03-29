package com.learn.springboot.practice.service.event.listener;

import com.learn.springboot.practice.service.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/***
 *  积分服务，用户注册成功后，加积分
 * @author lfq
 */
@Service
@Slf4j
public class ScoreService {

    @EventListener(value = UserRegisterEvent.class)
    @Order(1)
    @Async("myExecutor")
    public void addScore(UserRegisterEvent event) {
        log.info("addScore,event:{},ThreadName:{}", event.getSource(), Thread.currentThread().getName());
    }
}
