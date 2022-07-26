package com.learn.springboot.practice.utils.diff;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 比对字段注解
 *
 * @author lfq
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DiffProperty {
    /**
     * 属性中文名
     *
     * @return name
     */
    String name() default "";

    /**
     * 属性描述
     *
     * @return
     */
    String desc() default "";
}
