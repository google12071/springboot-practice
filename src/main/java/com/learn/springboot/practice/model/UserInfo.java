package com.learn.springboot.practice.model;

import com.learn.springboot.practice.model.constant.PhaseEnum;
import com.learn.springboot.practice.model.constant.SexEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description:
 * @Author lfq
 * @Date 2020/4/22
 **/
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 5216477842719016086L;
    private Integer id;
    private String userName;
    private Integer age;
    private SexEnum sex;
    private PhaseEnum phase;
    private Date createTime;
    private Date updateTime;
}
