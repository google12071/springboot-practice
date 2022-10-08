package com.learn.springboot.practice.service.event;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录事件
 *
 * @author lfq
 */
@Data
public class UserLoginEvent {
    private Long uid;
    private String userName;
    private LocalDateTime dateTime;
}
