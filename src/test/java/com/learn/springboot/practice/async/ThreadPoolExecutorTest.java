package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池使用示例
 */
@Slf4j
public class ThreadPoolExecutorTest {
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    //自己定义线程池
    ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, 2 * AVAILABLE_PROCESSORS,
            5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), r -> {
        Thread thread = new Thread(r);
        thread.setName("自定义线程");
        return thread;
    }, new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 线程A执行任务
     */
    @Test
    public void doSomeThingA() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            log.error("doSomeThingA error", e);
        }
        log.info("currentThead:{},doSomeThingA", Thread.currentThread().getName());
    }

    /**
     * 线程B执行任务
     */
    @Test
    public void doSomeThingB() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("doSomeThingA error", e);
        }
        log.info("currentThead:{},doSomeThingB", Thread.currentThread().getName());
    }


    public String doSomethingC() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("doSomeThingA error", e);
        }
        log.info("currentThead:{},doSomeThingC", Thread.currentThread().getName());
        return "C";
    }

    /**
     * 同步调用
     */
    @Test
    public void synchronizedInvoke() {
        long start = System.currentTimeMillis();
        doSomeThingA();
        doSomeThingB();
        log.info("cost:{}", System.currentTimeMillis() - start);
    }

    @Test
    public void asyncInvoke() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        doSomeThingA();
        EXECUTOR.execute(this::doSomeThingB);
        log.info("cost:{}", System.currentTimeMillis() - start);

        Future<String> future = EXECUTOR.submit(this::doSomethingC);
        log.info("getValue:{}", future.get());
    }
}
