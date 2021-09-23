package com.learn.springboot.practice.annotation;

import java.lang.annotation.*;

/**
 * 参数校验
 * @author lfq
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidate {
}
