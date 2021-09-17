package com.learn.springboot.practice.async;

        import lombok.extern.slf4j.Slf4j;
        import org.junit.Test;

        import java.util.concurrent.CompletableFuture;
        import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture测试类
 */
@Slf4j
public class CompletableFutureTest {

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
}
