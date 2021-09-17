package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.Doctor;
import com.learn.springboot.practice.bean.mapstruct.DoctorDTO;
import com.learn.springboot.practice.bean.mapstruct.Education;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author lfq
 */
@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mappings({
            @Mapping(source = "specialty", target = "specialization")
    })
    DoctorDTO toDto(Doctor doctor);

    /**
     * 组合映射
     *
     * @param doctor
     * @param education
     * @return
     */
    @Mappings({
            @Mapping(source = "doctor.specialty", target = "specialization"),
            @Mapping(source = "education.degreeName", target = "degree")
    })
    DoctorDTO toDto(Doctor doctor, Education education);

}
