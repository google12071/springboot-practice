package com.learn.springboot.practice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName IpInfo
 * @Description:
 * @Author lfq
 * @Date 2020/4/14
 **/
@Data
public class IpInfo implements Serializable {
    private static final long serialVersionUID = -6757847651962677969L;
    private Integer id;
    private Integer ip;
}
