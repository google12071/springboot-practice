package com.learn.springboot.practice.service.event.listener;

import com.learn.springboot.practice.service.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author lfq
 */
@Slf4j
@Service
public class EmailService {
    @EventListener
    public void sendEmail(UserRegisterEvent event) {
        log.info("sendEmail:event:{}", event.getSource());
    }
}
