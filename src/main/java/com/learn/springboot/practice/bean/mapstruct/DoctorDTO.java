package com.learn.springboot.practice.bean.mapstruct;

import lombok.Data;

import java.util.List;

/**
 * @author lfq
 */
@Data
public class DoctorDTO {
    private Integer id;
    private String name;
    private String specialization;
    private String degree;
    private List<PatientDTO> patientDTOList;
    private GenderEnum genderEnum;
}
