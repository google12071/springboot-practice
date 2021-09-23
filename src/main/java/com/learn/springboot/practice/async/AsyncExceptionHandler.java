package com.learn.springboot.practice.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import java.lang.reflect.Method;

/**
 * 针对有返回值的异常处理类（自定义异常处理类）
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("asyncExceptionHandler error,method:{},objects:{}", throwable, method.getName(), objects);
    }
}
