package com.learn.springboot.practice.service.event.listener;

import com.google.common.eventbus.Subscribe;
import com.learn.springboot.practice.service.event.UserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Guava EventBus Listener
 *
 * @author lfq
 */
@Component
@Slf4j
public class GeventListener {

    @Subscribe
    public void processUserLogin(UserLoginEvent loginEvent) {
        try {
            log.info("processUserLogin,userInfo:{},Thread:{}", loginEvent, Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("processUserLogin error", e);
        }
    }
}
