package com.learn.springboot.practice.pojo;

import lombok.Data;

@Data
public class CompositeDTO {
    private BossDTO bossDTO;
    private CarDTO carDTO;
    private String composite;
}
