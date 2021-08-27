package com.learn.springboot.practice.bean;

import lombok.Data;

/**
 * @author lfq
 */
@Data
public class BBean {
    public BBean(String id) {
        this.id = id;
    }

    private String id;
}
