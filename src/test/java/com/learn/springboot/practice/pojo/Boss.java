package com.learn.springboot.practice.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Boss {
    private String companyName;
    private List<Car> carList;
}
