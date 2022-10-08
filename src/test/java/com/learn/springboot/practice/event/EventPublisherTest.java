package com.learn.springboot.practice.event;

import com.learn.springboot.practice.BaseTest;
import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.service.event.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事件发布监听机制
 */
public class EventPublisherTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void userRegister() {
        User user = new User();
        user.setName("test");
        user.setAge(18);
        userService.register(user);
    }

    @Test
    public void userLogin() {
        User user = new User();
        user.setName("test");
        user.setAge(18);
        userService.login(user);
    }
}
