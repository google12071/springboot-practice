package com.learn.springboot.practice.bean;

import lombok.Data;

/**
 * @author lfq
 */
@Data
public class ABean {
    public ABean(String id) {
        this.id = id;
    }

    private String id;
}
