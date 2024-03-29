package com.learn.springboot.practice.bean;

import com.learn.springboot.practice.BaseTest;
import com.learn.springboot.practice.bean.mapper.CarMapper;
import com.learn.springboot.practice.bean.mapper.DoctorMapper;
import com.learn.springboot.practice.bean.mapper.SexMapper;
import com.learn.springboot.practice.bean.mapstruct.*;
import com.learn.springboot.practice.enums.CarTypeEnum;
import com.learn.springboot.practice.pojo.Boss;
import com.learn.springboot.practice.pojo.Car;
import com.learn.springboot.practice.pojo.CarDTO;
import com.learn.springboot.practice.pojo.CarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MapStructTest extends BaseTest {

    @Autowired
    private CarMapper carMapper;

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
        mapper.setName(dto, "fq");
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

    @Test
    public void mapStructTest(){
        Car car = new Car();
        car.setMake("测试");
        car.setNum(10);
        car.setNumberOfSeats(4);
        CarType type = new CarType();
        type.setCarType(CarTypeEnum.SUV);
        type.setPrice(new BigDecimal(100));
        car.setType(type);

        CarDTO carDTO = carMapper.car2CarDTO(car);
        System.out.println(carDTO);
    }
}
