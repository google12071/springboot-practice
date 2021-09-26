package com.learn.springboot.practice.pojo;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private Integer teachId;
    private String name;

    public Student(Integer id, Integer teachId, String name) {
        this.id = id;
        this.teachId = teachId;
        this.name = name;
    }
}
