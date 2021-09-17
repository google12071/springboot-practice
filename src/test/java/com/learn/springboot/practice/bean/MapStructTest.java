package com.learn.springboot.practice.bean;

import com.learn.springboot.practice.bean.mapper.DoctorMapper;
import com.learn.springboot.practice.bean.mapstruct.Doctor;
import com.learn.springboot.practice.bean.mapstruct.DoctorDTO;
import com.learn.springboot.practice.bean.mapstruct.Education;
import com.learn.springboot.practice.bean.mapstruct.Patient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


        Patient patient = new Patient();
        patient.setId("123413");
        patient.setPatientName("张三");
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        doctor.setPatientList(patientList);


        Education education = new Education();
        education.setDegreeName("硕士");
        education.setInstitute("计算机学院");
        education.setYearOfPassing(6);

        DoctorDTO dto2 = mapper.toDto(doctor, education);
        System.out.println(dto2);


        mapper.updateModel(dto2, doctor);
        System.out.println(dto2);
        System.out.println(doctor);
    }
}
