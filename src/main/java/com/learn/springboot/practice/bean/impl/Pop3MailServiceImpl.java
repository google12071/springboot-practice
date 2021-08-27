package com.learn.springboot.practice.bean.impl;

import com.learn.springboot.practice.bean.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author lfq
 */
@Component
@Slf4j
@ConditionalOnProperty(value = "spring.email.protocol", havingValue = "pop3")
public class Pop3MailServiceImpl implements MailService {
    @Override
    public void sendEmail(String message) {
        log.info("pop3,message:{}", message);
    }
}
