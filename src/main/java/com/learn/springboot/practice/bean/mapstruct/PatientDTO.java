package com.learn.springboot.practice.bean.mapstruct;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author lfq
 */
@Data
public class PatientDTO {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
}
