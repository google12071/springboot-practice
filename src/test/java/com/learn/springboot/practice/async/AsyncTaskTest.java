package com.learn.springboot.practice.async;

import com.learn.springboot.practice.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * Spring异步执行任务
 */
@Slf4j
public class AsyncTaskTest extends BaseTest {

    /**
     * 注入执行器
     */
    @Resource(name = "myExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 演示需要，用于控制任务全部执行完毕后关闭线程池
     */
    private static final CountDownLatch latch = new CountDownLatch(8);

    /**
     * 异步任务抽象
     */
    public static class AsyncTask implements Runnable {
        /**
         * 任务消息
         */
        private String message;

        public AsyncTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            //模拟耗时
            try {
                Thread.sleep(2000);
                log.info("Thread:{},message:{}", Thread.currentThread().getName(), message);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void printMessage() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 8; i++) {
            executor.execute(new AsyncTask("message:" + i));
        }
        //处理有返回值的异步任务
        Future<Object> future = executor.submit(new Callable<Object>() {
            @Override
            public String call() throws Exception {
                log.info("Thread:{},invoke", Thread.currentThread().getName());
                return "hello";
            }
        });
        log.info("异步返回value:{}", future.get());
        latch.await();
        //任务全部执行完成后关闭线程池
        executor.shutdown();
    }


    @Autowired
    private AsyncAnnotationExample example;

    @Test
    public void asyncMethod() {
        example.asyncMethod();
    }

    @Test
    public void futureResult() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = example.futureResult();
        log.info("future:{}", future.get());
    }

    /**
     * Future模式callback
     */
    @Test
    public void futureCallback() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = example.futureResult();
        future.whenCompleteAsync((s, throwable) -> log.info("s:{},error:{}", s, throwable));
        log.info("future:{}", future.get());
    }
}
