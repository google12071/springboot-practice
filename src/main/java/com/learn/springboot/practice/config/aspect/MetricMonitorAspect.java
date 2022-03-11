package com.learn.springboot.practice.config.aspect;

import com.learn.springboot.practice.annotation.MetricMonitor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 指标监控AOP切面
 *
 * @author lfq
 */
@Slf4j
@Component
@Aspect
public class MetricMonitorAspect {
    /**
     * AOP切点
     */
    @Pointcut("@annotation(com.learn.springboot.practice.annotation.MetricMonitor)")
    public void monitorPointCut() {
    }

    /**
     * 环绕切点
     *
     * @return
     */
    @Around("monitorPointCut()")
    public Object MetricCollector(ProceedingJoinPoint joinPoint) {
        //方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //目标方法
        Method method = signature.getMethod();
        MetricMonitor metricMonitor = method.getAnnotation(MetricMonitor.class);
        //非切点方法直接返回
        try {
            Object obj = joinPoint.proceed();
            if (metricMonitor == null || !metricMonitor.required()) {
                return obj;
            }
            //操作类型
            String operateType = metricMonitor.operateType();
            //上报操作成功消息
            sendMqMessage(operateType, 1);

            return obj;
        } catch (Throwable e) {
            log.error("方法执行异常", e);
            //发送操作失败消息
            sendMqMessage(metricMonitor.operateType(), 1);
        }
        return null;
    }

    private void sendMqMessage(String operateType, Integer status) {
        //医生注册
        if ("DOCTOR_REGISTER".equals(operateType)) {
        }
        //todo 创建订单...
    }
}
