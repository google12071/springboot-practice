package com.learn.springboot.practice.utils.diff;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 对象唯一key
 *
 * @author lfq
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DiffUniqueKey {
    /**
     * 唯一key名称
     *
     * @return
     */
    String name() default "id";
}
