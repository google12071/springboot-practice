package com.learn.springboot.practice.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedissonLock
 * @Description:
 * @Author lfq
 * @Date 2020/4/23
 **/
@Slf4j
@Component
public class RedissonLockUtil {
    @Autowired
    private RedissonClient redissonClient;

    public boolean tryLock() {
        RLock rLock = redissonClient.getLock("LOCK");
        boolean isLock = false;
        try {
            isLock = rLock.tryLock();
            if (isLock) {
                log.info("线程" + Thread.currentThread().getName() + ",成功争抢到锁");
                Thread.sleep(5 * 1000);
                isLock = true;
            } else {
                log.info("线程" + Thread.currentThread().getName() + ",争抢锁失败");
                isLock = false;
            }
        } catch (InterruptedException e) {
            log.error("tryLock error", e);
        } finally {
            if (isLock) {
                rLock.unlock();
                log.info(Thread.currentThread().getName() + "成功释放锁");
            }
        }
        return isLock;
    }
}
