package com.learn.springboot.practice.service.event.listener;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.learn.springboot.practice.service.event.config.EventBusBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lfq
 */
@Component
@Slf4j
public class GeventPublisher {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private GeventListener listener;

    @PostConstruct
    public void init() {
        asyncEventBus.register(listener);
    }

    @PreDestroy
    public void destroy() {
        asyncEventBus.unregister(listener);
    }

    public void post(Object object) {
        asyncEventBus.post(object);
    }
}
