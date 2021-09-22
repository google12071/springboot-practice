package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * CompletableFuture测试类
 */
@Slf4j
public class CompletableFutureTest {

    private static final ThreadPoolExecutor bizExecutor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    /**
     * 主动完成任务
     */
    @Test
    public void completeTask() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("currentThread interrupt:{}", Thread.currentThread().getName(), e);
            }
            return "hello";
        });

        //阻塞等待
        future.complete("return");
        //主动通知任务结束
        String value = future.get();
        log.info("value:{},cost:{}", value, (System.currentTimeMillis() - start));
    }

    /**
     * 无返回值异步任务，默认线程池
     */
    @Test
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            //模拟线程执行任务
            try {
                log.info("Thread start:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread end:{}", Thread.currentThread().getName());
        });
        log.info("future,result:{}", future.get());
    }

    /**
     * 无返回值异步任务,自定义线程池
     */
    @Test
    public void runAsyncWithBizExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            //模拟线程执行任务
            try {
                log.info("Thread start:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread end:{}", Thread.currentThread().getName());
        }, bizExecutor);
        log.info("future,result:{}", future.get());
    }

    /**
     * 有返回值异步任务，默认线程池
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void runSupplierAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //模拟线程执行任务
            try {
                log.info("Thread start:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread end:{}", Thread.currentThread().getName());
            return "result";
        });
        log.info("future,result:{}", future.get());
    }

    /**
     * 有返回值异步任务，自定义线程池
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void runSupplierAsyncWithBizExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //模拟线程执行任务
            try {
                log.info("Thread start:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread end:{}", Thread.currentThread().getName());
            return "result";
        }, bizExecutor);
        log.info("future,result:{}", future.get());
    }

    /**
     * 任务A执行完后，任务B执行，任务B无法获取到任务A的执行结果
     */
    @Test
    public void thenRun() {

    }

}
