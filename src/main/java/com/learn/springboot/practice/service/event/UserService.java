package com.learn.springboot.practice.service.event;

import com.learn.springboot.practice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 事件发布者
 *
 * @author lfq
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ApplicationEventPublisher publisher;

    /***
     * 用户注册
     * @param user
     */
    public void register(User user) {
        log.info("start user register,user:{}", user);
        publisher.publishEvent(new UserRegisterEvent(user));
        log.info("user register end,user:{}", user);
    }
}
