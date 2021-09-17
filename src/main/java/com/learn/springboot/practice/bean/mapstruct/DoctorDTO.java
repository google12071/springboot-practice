package com.learn.springboot.practice.bean.mapstruct;

import lombok.Data;

/**
 * @author lfq
 */
@Data
public class DoctorDTO {
    private Integer id;
    private String name;
    private String specialization;
    private String degree;
}
