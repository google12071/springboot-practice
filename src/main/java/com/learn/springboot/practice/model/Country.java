package com.learn.springboot.practice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Country
 * @Description:
 * @Author lfq
 * @Date 2020/4/22
 **/
@Data
public class Country implements Serializable {
    private static final long serialVersionUID = 3406016400684206820L;
    private Integer id;
    private String countryName;
    private String countryCode;
}
