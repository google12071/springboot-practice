package com.learn.springboot.practice.retry;

import com.learn.springboot.practice.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.retry.*;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.stats.DefaultStatisticsRepository;
import org.springframework.retry.stats.StatisticsListener;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * @ClassName RetryTest
 * @Description: Spring-Retry失败重试(支持构建RetryTemplate自动配置和基于注解声明公)
 * @Author lfq
 * @Date 2021/7/5
 **/
@Slf4j
public class RetryTest extends BaseTest {

    @Autowired
    private ThirdPartyService partyService;

    /**
     * 无状态重试（1.3.x以上版本支持builder模式）
     */
    @Test
    @Deprecated
    public void retryNoState() {
        RetryTemplate template = new RetryTemplate();
        //重试策略：次数重试策略
        RetryPolicy retryPolicy = new SimpleRetryPolicy(3);
        //退避策略：指数退避策略
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(100);
        backOffPolicy.setMaxInterval(3000);
        backOffPolicy.setMultiplier(2);
        //当前线程休眠一定时间
        backOffPolicy.setSleeper(new ThreadWaitSleeper());
        //配置重试策略及退避策略
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);

        //当重试失败后，抛出异常
        String result1 = template.execute(context -> {
            log.info("result1 currentThread:{},retryCount:{}", Thread.currentThread().getName(), context.getRetryCount());
            throw new RuntimeException("timeout");
        });

        log.info("result1:{}", result1);

        //当重试失败后，执行RecoveryCallback
        String result2 = template.execute(context -> {
            log.info("result2 currentThread:{},retryCount:{}", Thread.currentThread().getName(), context.getRetryCount());
            throw new RuntimeException("timeout");
        }, context -> {
            log.info("default currentThread:{},retryCount:{}", Thread.currentThread().getName(), context.getRetryCount());
            return "default";
        });
        log.info("result2:{}", result2);
    }

    /**
     * 有状态模式，1.3.x支持builder模式
     */
    @Test
    public void retryOnState() {
        RetryTemplate template = new RetryTemplate();
        //当前状态的名称，当把状态放入缓存时，通过该key查询获取
        Object key = "mykey";
        //是否每次都重新生成上下文还是从缓存中查询，即全局模式（如熔断器策略时从缓存中查询）
        boolean isForceRefresh = true;
        //对DataAccessException进行回滚
        BinaryExceptionClassifier rollbackClassifier =
                new BinaryExceptionClassifier(Collections.singleton(DataAccessException.class));
        RetryState state = new DefaultRetryState(key, isForceRefresh, rollbackClassifier);

        String result = template.execute(context -> {
            System.out.println("retry count:" + context.getRetryCount());
            throw new TypeMismatchDataAccessException("数据访问异常");
        }, context -> "default", state);
        log.info("result:{}", result);

    }

    @Test
    public void retryByBuilder() {
        DefaultStatisticsRepository repository = new DefaultStatisticsRepository();
        RetryTemplate template = RetryTemplate.builder()
                .maxAttempts(5)
                .exponentialBackoff(100, 2, 50000)
                .retryOn(RuntimeException.class)
                .withListener(new StatisticsListener(repository))
                .build();
        //当重试失败后，执行RecoveryCallback
        String result2 = template.execute(context -> {
            log.info("result2 currentThread:{},retryCount:{}", Thread.currentThread().getName(), context.getRetryCount());
            context.setAttribute(RetryContext.NAME, "method.key");
            throw new RuntimeException("timeout");
        }, context -> {
            log.info("default currentThread:{},retryCount:{}", Thread.currentThread().getName(), context.getRetryCount());
            return "default";
        });
        log.info("result2:{}", result2);
        RetryStatistics statistics = repository.findOne("method.key");
        System.out.println(statistics);
    }


    /**
     * 注解+声明式重试
     */
    @Test
    public void retryByStatement() {
        String s = "retry1234";
        String result = partyService.queryThirdMessage(s);
        log.info("result:{}", result);

        int value = partyService.queryValue("test123432");
        log.info("value:{}", value);
    }


}
