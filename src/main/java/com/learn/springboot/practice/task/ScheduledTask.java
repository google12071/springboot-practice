package com.learn.springboot.practice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        log.info("任务执行开始时间：{}", LocalDateTime.now());
        int random = getRandom(100);
        if (random > 50) {
            throw new RuntimeException("随机数大于50");
        }
        log.info("任务执行结束时间：{},任务执行结果:{}", LocalDateTime.now(), random);
    }

    public class AllocateMemory implements Runnable {
        private int num;

        public AllocateMemory(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[num * 1024 * 1024];
            log.info("Thread:{},allocateMemory success", Thread.currentThread().getName());
        }
    }

    public int getRandom(int num) {
        int r = new Random().nextInt(num);
        try {
            Thread.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r;
    }

}
