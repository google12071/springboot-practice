package com.learn.springboot.practice.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户注册事件
 *
 * @author lfq
 */
public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
