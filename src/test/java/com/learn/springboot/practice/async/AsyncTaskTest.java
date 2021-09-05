package com.learn.springboot.practice.async;

import com.learn.springboot.practice.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * Spring异步执行任务
 */
@Slf4j
public class AsyncTaskTest extends BaseTest {
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

    /**
     * 注入执行器
     */
    @Resource(name = "myExecutor")
    private ThreadPoolTaskExecutor executor;

    @Test
    public void printMessage() throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            executor.execute(new AsyncTask("message:" + i));
        }
        latch.await();
        executor.shutdown();
    }
}
