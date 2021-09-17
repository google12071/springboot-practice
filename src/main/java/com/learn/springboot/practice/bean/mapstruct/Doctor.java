package com.learn.springboot.practice.bean.mapstruct;

import lombok.Data;

import java.util.List;

/**
 * @author lfq
 */
@Data
public class Doctor {
    private Integer id;
    private String name;
    private String specialty;
    private List<Patient> patientList;
    private SexEnum sexEnum;
}
