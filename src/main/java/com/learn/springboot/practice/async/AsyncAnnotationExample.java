package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 基于注解的Spring异步demo
 */
@Slf4j
@Component
//开启异步执行
@EnableAsync
public class AsyncAnnotationExample {
    /**
     * 异步执行,可指定线程池
     */
    @Async("myExecutor")
    public void asyncMethod() {
        log.info("Thread:{},asyncMethod invoke", Thread.currentThread().getName());
        throw new RuntimeException("操作异常");
    }

    /**
     * 支持Future模式，获取返回值
     */
    @Async("myExecutor")
    public CompletableFuture<String> futureResult() throws InterruptedException {
        //创建future
        CompletableFuture<String> future = new CompletableFuture<>();
        Thread.sleep(2000);
        log.info("Thread:{} futureResult", Thread.currentThread().getName());
        future.complete("done");
        return future;
    }


}
