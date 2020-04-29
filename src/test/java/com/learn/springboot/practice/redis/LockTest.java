package com.learn.springboot.practice.redis;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.utils.RedissonLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName LockTest
 * @Description:
 * @Author lfq
 * @Date 2020/4/23
 **/

@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class LockTest {

    @Autowired
    private RedissonLockUtil lockBean;

    private CountDownLatch mainCount = new CountDownLatch(1);
    private CountDownLatch threadCount = new CountDownLatch(20);

    @Test
    public void tryLock() {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    mainCount.await();
                    lockBean.tryLock();
                    threadCount.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        mainCount.countDown();
        try {
            threadCount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
