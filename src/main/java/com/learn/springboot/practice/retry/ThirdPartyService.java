package com.learn.springboot.practice.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ClassName ThirdPartyService
 * @Description:模拟三方耗时操作，超时或抛异常后，Spring-Retry根据配置策略进行失败重试
 * @Author lfq
 * @Date 2021/7/6
 **/
@Service
@Slf4j
public class ThirdPartyService {
    @Retryable(value = RuntimeException.class, maxAttempts = 5, backoff = @Backoff(delay = 2000L, maxDelay = 5000L, multiplier = 1.5),
            recover = "messageRecovery")
    public String queryThirdMessage(String s) {
        log.info("Thread:{},str:{}", Thread.currentThread().getName(), s);
        String message = "hello," + s;
        if (s.startsWith("retry")) {
            throw new RuntimeException("调用三方异常，进行失败重试");
        }
        return message;
    }
    @Recover
    private String messageRecovery(RuntimeException e, String s) {
        log.info("Thread:{},str:{} invoke recovery", Thread.currentThread().getName(), s);
        log.warn("recovery error,str:{}", s, e);
        return "default";
    }


    @Retryable(value = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000),
            recover = "valueRecover")
    public Integer queryValue(String s) {
        log.info("Thread:{},queryValue str:{}", Thread.currentThread().getName(), s);
        if (s.startsWith("test")) {
            throw new RuntimeException("调用三方异常，进行失败重试");
        }
        return new Random().nextInt(10) + 1;
    }

    @Recover
    private Integer valueRecover(RuntimeException e, String s) {
        log.warn("valueRecovery,String:{}", s, e);
        return 0;
    }

}
