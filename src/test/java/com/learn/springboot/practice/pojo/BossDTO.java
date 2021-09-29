package com.learn.springboot.practice.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BossDTO {
    private String company;
    private List<CarDTO> carDTOList;
}
