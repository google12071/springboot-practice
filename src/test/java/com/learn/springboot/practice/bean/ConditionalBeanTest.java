package com.learn.springboot.practice.bean;

import com.learn.springboot.practice.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 条件装配
 */
@Slf4j
public class ConditionalBeanTest extends BaseTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendEmail() {
        mailService.sendEmail("hello");
    }
}
