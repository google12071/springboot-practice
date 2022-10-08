package com.learn.springboot.practice.service.event.listener;

import com.learn.springboot.practice.service.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lfq
 */
@Slf4j
@Component
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("UserRegisterEvent error", e);
        }
        log.info("sendEmail:event:{},ThreadName:{}", event.getSource(), Thread.currentThread().getName());
    }
}
