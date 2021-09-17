package com.learn.springboot.practice.bean;

import com.learn.springboot.practice.bean.mapper.DoctorMapper;
import com.learn.springboot.practice.bean.mapstruct.Doctor;
import com.learn.springboot.practice.bean.mapstruct.DoctorDTO;
import com.learn.springboot.practice.bean.mapstruct.Education;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MapStructTest {

    @Test
    public void primaryMapping() {
        DoctorMapper mapper = DoctorMapper.INSTANCE;
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("test");
        doctor.setSpecialty("tom");
        DoctorDTO dto = mapper.toDto(doctor);
        System.out.println(dto);


        Education education = new Education();
        education.setDegreeName("硕士");
        education.setInstitute("计算机学院");
        education.setYearOfPassing(6);

        DoctorDTO dto2=mapper.toDto(doctor,education);
        System.out.println(dto2);
    }
}
