package com.learn.springboot.practice.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;
    private BigDecimal price;
}
