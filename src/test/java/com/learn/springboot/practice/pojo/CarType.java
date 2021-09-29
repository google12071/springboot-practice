package com.learn.springboot.practice.pojo;

import com.learn.springboot.practice.enums.CarTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarType {
    private CarTypeEnum carType;
    private BigDecimal price;
}
