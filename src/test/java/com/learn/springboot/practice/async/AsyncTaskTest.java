package com.learn.springboot.practice.async;

import com.learn.springboot.practice.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring异步执行任务
 */
@Slf4j
public class AsyncTaskTest extends BaseTest {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入执行器
     */
    @Autowired
    private CustomizeExecutor executor;

    @Test
    public void printMessage() {
        ThreadPoolTaskExecutor taskExecutor = executor.taskExecutor();
        for (int i = 0; i < 8; i++) {
            taskExecutor.execute(new AsyncTask("message:" + i));
        }
    }
}
