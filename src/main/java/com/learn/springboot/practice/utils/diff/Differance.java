package com.learn.springboot.practice.utils.diff;

import lombok.Data;

/**
 * @author lfq
 */
@Data
public class Differance {
    /**
     * 属性名
     */
    private String fieldName;
    /**
     * 属性名称
     */
    private String name;

    /**
     * 旧值
     */
    private Object oldValue;

    /**
     * 新值
     */
    private Object newValue;

    /**
     * 变更类型
     */
    private String diffType;
}
