package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池执行器Bean
 */
@Slf4j
@Configuration
@EnableAsync
public class CustomizeExecutor implements AsyncConfigurer {
    @Override
    @Bean("myExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //配置核心线程
        taskExecutor.setCorePoolSize(5);
        //配置最大线程数量
        taskExecutor.setMaxPoolSize(20);
        //超过核心线程数，空闲线程多久被回收
        taskExecutor.setKeepAliveSeconds(60);
        //线程池阻塞队列长度（capacity>0-->LinkedBlockingQueue else SynchronousQueue）
        taskExecutor.setQueueCapacity(20);
        //配置拒绝策略(线程池队列满后，新任务不再异步执行，改为调用线程执行)
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待任务执行完成后才关闭JVM（默认false，可能导致线程中断异常）
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

    /**
     * 异常处理器，用于捕捉异步任务执行异常
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

}
