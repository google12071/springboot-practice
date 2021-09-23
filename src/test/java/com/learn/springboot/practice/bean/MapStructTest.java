package com.learn.springboot.practice.bean;

import com.learn.springboot.practice.bean.mapper.DoctorMapper;
import com.learn.springboot.practice.bean.mapper.SexMapper;
import com.learn.springboot.practice.bean.mapstruct.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MapStructTest {

    @Test
    public void primaryMapping() {
        //普通映射
        DoctorMapper mapper = DoctorMapper.INSTANCE;
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("test");
        doctor.setSpecialty("tom");
        doctor.setSexEnum(SexEnum.WOMEN);
        DoctorDTO dto = mapper.toDto(doctor);
        System.out.println(dto);


        Patient patient = new Patient();
        patient.setId("123413");
        patient.setPatientName("张三");
        patient.setBirthDay("1993-11-05");
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        doctor.setPatientList(patientList);


        Education education = new Education();
        education.setDegreeName("硕士");
        education.setInstitute("计算机学院");
        education.setYearOfPassing(6);

        //组合映射
        DoctorDTO dto2 = mapper.toDto(doctor, education);
        System.out.println(dto2);

    }


    /**
     * 枚举映射
     */
    @Test
    public void enumsMapper() {

        SexMapper sexMapper = SexMapper.INSTANCE;
        SexEnum sexEnum = sexMapper.toSex(GenderEnum.FEMALE);
        GenderEnum genderEnum = sexMapper.toGender(SexEnum.WOMEN);
        System.out.println(sexEnum);
        System.out.println(genderEnum);
    }
}
