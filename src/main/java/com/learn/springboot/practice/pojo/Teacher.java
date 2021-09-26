package com.learn.springboot.practice.pojo;

import lombok.Data;

@Data
public class Teacher {
    private Integer id;
    private String name;

    public Teacher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
