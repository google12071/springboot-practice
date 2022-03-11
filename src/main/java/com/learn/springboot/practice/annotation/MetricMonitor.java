package com.learn.springboot.practice.annotation;

/**
 * 业务指标监控
 *
 * @author lfq
 */
public @interface MetricMonitor {
    /**
     * 操作类型
     *
     * @return
     */
    String operateType() default "";

    /**
     * 是否生效，置为false后不再上报
     *
     * @return
     */
    boolean required() default true;
}
