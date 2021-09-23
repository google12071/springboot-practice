package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
     * <p>
     * 任务A->任务B
     */
    @Test
    public void thenRun() throws ExecutionException, InterruptedException {
        //创建任务A
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Thread:{}", Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "futureA";
        });

        //创建任务B，在A完成后触发回调
        CompletableFuture futureB = futureA.thenRun(() -> {
            log.info("Thread:{},callback invoke", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("futureResult:{}", futureB.get());
    }

    /**
     * 任务A执行完后，任务B执行，任务B可以针对任务A计算结果进行加工
     * <p>
     * 任务A->任务B
     */
    @Test
    public void thenAccept() throws ExecutionException, InterruptedException {
        //创建任务A
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Thread:{}", Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "AResult";
        });

        //创建任务B，在A完成后触发回调,任务B可以针对A的计算结果进行处理
        CompletableFuture futureB = futureA.thenAccept(new Consumer<String>() {
            /**
             * 参数s为任务A的计算结果，任务B可对其进行加工处理
             *
             * @param s
             */
            @Override
            public void accept(String s) {
                try {
                    log.info("Thread:{},value:{}", Thread.currentThread().getName(), s);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //因为futureB无返回值，所以get任务计算结果为空
        log.info("futureResult:{}", futureB.get());
    }

    /**
     * 任务A执行完后，任务B执行，任务B可以针对任务A计算结果进行加工,并返回任务B的计算结果
     * <p>
     * 任务A->任务B
     */
    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        //创建任务A
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Thread:{}", Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "AResult";
        });

        //创建任务B，在A完成后触发回调,任务B可以针对A的计算结果进行处理
        CompletableFuture<String> futureB = futureA.thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                try {
                    log.info("Thread:{},AResult:{}", Thread.currentThread().getName(), s);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s + "," + "BResult";
            }
        });
        //因为futureB无返回值，所以get任务计算结果为空
        log.info("futureResult:{}", futureB.get());

        //任务A计算结果，任务B可以继续处理
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("一阶段：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("二阶段：" + result);
            return result;
        });
        System.out.println("最终结果：" + future.get());
    }

    /**
     * 任务A执行完成后，触发回调,其中回调处理不阻塞当前线程
     */
    @Test
    public void whenComplete() throws ExecutionException, InterruptedException {
        //创建任务A
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Thread:{}", Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "AResult";
        });

        //回调处理不阻塞当前线程
        futureA.whenComplete((s, throwable) -> {
            try {
                log.info("Thread:{},s:{}", Thread.currentThread().getName(), s);
                Thread.sleep(3000);
            } catch (Exception e) {
                log.error("whenComplete error", throwable);
            }
        });
        //此处主线程先阻塞等待任务执行结果，不然唯一的用户线程退出后，JVM进程就退出了
        log.info("futureA result:{}", futureA.get());
    }

    /**
     * 异步任务组合,任务A计算后，任务B加工处理后返回,实现任务合并运算
     */
    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    log.info("Thread:{}", Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.error("futureA invoke error", e);
                }
                return "AResult";
            }
        });

        CompletableFuture<String> futureB = futureA.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        log.info("result:{}", futureB.get());

        //compose方法将生成一个新的CompletableFuture
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(3);
                System.out.println("第一阶段：" + number);
                return number;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = param * 2;
                        System.out.println("第二阶段：" + number);
                        return number;
                    }
                });
            }
        });
        System.out.println("最终结果: " + future.get());
    }

    //实现任务的合并运算
    @Test
    public void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureA
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> futureB = futureA.thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        log.info("futureB:{}", futureB.get());
    }

    /**
     * 多任务并行执行，任务全部执行完毕后返回计算结果
     */
    @Test
    public void allOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成！";
        });

        CompletableFuture<Void> combineFuture = CompletableFuture.allOf(future1, future2);
        try {
            //阻塞等待任务全部返回
            combineFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //future1,future2均完成
        System.out.println("future1: " + future1.isDone() + "，future2: " + future2.isDone());
    }

    /**
     * 多任务并行计算，任意一个结果返回，则返回计算结果
     */
    @Test
    public void runAny() throws ExecutionException, InterruptedException {
        Random random = new Random();
        //随机sleep时间
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "AResult";
        });

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "BResult";
        });
        //任意一个任务计算结束，则返回计算结果
        CompletableFuture<Object> result = CompletableFuture.anyOf(futureA, futureB);
        log.info("result:{}", result.get());
    }

    /**
     * 异常处理
     */
    @Test
    public void handleException() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {

            }
            int i = 12 / 0;
            System.out.println("执行结束！");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                try {
                    System.out.println("执行完成！");
                } catch (Throwable e) {
                    log.error("invoke error", action);
                }
            }
        });

        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                try {
                    System.out.println("执行完成");
                } catch (Throwable e) {
                    log.error("invoke error",t);
                }
                return null;
            }
        }).join();
    }
}
