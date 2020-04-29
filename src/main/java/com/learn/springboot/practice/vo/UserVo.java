package com.learn.springboot.practice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Description:用户信息
 * @Author lfq
 * @Date 2020/4/29
 **/
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 7124642170840311171L;
    private Long uid;
    private String name;
    private Integer age;
    private String address;
    private String des;
    private Double money;
}
