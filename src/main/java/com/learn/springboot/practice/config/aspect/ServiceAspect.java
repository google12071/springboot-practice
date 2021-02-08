package com.learn.springboot.practice.config.aspect;

import com.learn.springboot.practice.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @ClassName ServiceAspect
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
@Slf4j
@Component
@Aspect
public class ServiceAspect {
    @Pointcut("@annotation(com.learn.springboot.practice.annotation.ParamValidate)")
    public void pointCut() {
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "throwable")
    public void afterThrow(JoinPoint joinPoint, Throwable throwable) {
        if (throwable instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException) throwable;
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<?> v : violations) {
                sb.append(v.getMessageTemplate()).append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            throw new BizException("param_error", sb.toString());
        }
    }
}
