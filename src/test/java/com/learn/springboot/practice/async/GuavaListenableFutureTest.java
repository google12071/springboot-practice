package com.learn.springboot.practice.async;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Guava ListenableFuture测试类
 * 异步测试，ListenableFuture相对于JUC的FutureTask多了回调
 */
@Slf4j
public class GuavaListenableFutureTest {
    //线程池中线程个数
    private static final int POOL_SIZE = 50;

    //带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));


    @Test
    public void testListenableFuture() {
        final List<String> value = Collections.synchronizedList(new ArrayList<>());

        try {
            List<ListenableFuture<String>> futures = new ArrayList<>();

            // 将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例，
            // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
            // 在onSuccess方法中将查询的结果存入到集合中

            for (int i = 0; i < 1; i++) {
                final int index = i;
                if (i == 9) {
                    Thread.sleep(500 * i);
                }

                ListenableFuture<String> sfuture = service
                        .submit(() -> {
                            long time = System.currentTimeMillis();
                            log.info("Finishing sleeping task{}: {}", index, time);
                            return String.valueOf(5/0);
                        });
                sfuture.addListener(() -> log.info("Listener be triggered for task{}.", index), service);

                Futures.addCallback(sfuture, new FutureCallback<String>() {
                    public void onSuccess(String result) {
                        log.info("Add result value into value list {}.", result);
                        value.add(result);
                    }

                    public void onFailure(Throwable t) {
                        log.info("Add result value into value list error.", t);
                        throw new RuntimeException(t);
                    }
                }, MoreExecutors.directExecutor());
                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(sfuture);
            }

            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取的是所有任务中用时最长的一个
            ListenableFuture<List<String>> allAsList = Futures.allAsList(futures);
            allAsList.get();
            log.info("All sub-task are finished.");
        } catch (Exception ignored) {
        }
    }
}
