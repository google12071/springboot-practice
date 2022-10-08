package com.learn.springboot.practice.service.event.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.learn.springboot.practice.async.CustomizeExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EventBusBean {
    @Autowired
    private CustomizeExecutor executor;

    @Bean(value = "eventBus")
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean(value = "asyncEventBus")
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(Objects.requireNonNull(executor.getAsyncExecutor()));
    }
}
