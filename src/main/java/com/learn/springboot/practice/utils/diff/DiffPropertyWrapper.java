package com.learn.springboot.practice.utils.diff;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lfq
 */
@Data
@AllArgsConstructor
public class DiffPropertyWrapper {
    /**
     * 属性名
     */
    private String name;
    /**
     * 属性中文名
     */
    private String nameCn;

    /**
     * 属性描述
     */
    private String desc;

    /**
     * 属性值
     */
    private Object value;
}
