package com.learn.springboot.practice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description:
 * @Author lfq
 * @Date 2020/4/14
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1926149153920197749L;
    private Long uid;
    private String name;
    private Integer age;
    private String address;
    private String des;
    private Double money;
    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }
}
