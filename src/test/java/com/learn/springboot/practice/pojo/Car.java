package com.learn.springboot.practice.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType type;
    private int num;
}
