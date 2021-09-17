package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.Doctor;
import com.learn.springboot.practice.bean.mapstruct.DoctorDTO;
import com.learn.springboot.practice.bean.mapstruct.Education;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author lfq
 */
@Mapper(uses = {PatientMapper.class, SexMapper.class})
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    /**
     * 子对象映射
     *
     * @param doctor
     * @return
     */
    @Mappings({
            @Mapping(source = "specialty", target = "specialization"),
            @Mapping(source = "doctor.patientList", target = "patientDTOList"),
            @Mapping(source = "doctor.sexEnum", target = "genderEnum")
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
            @Mapping(source = "education.degreeName", target = "degree"),
            @Mapping(source = "doctor.patientList", target = "patientDTOList")
    })
    DoctorDTO toDto(Doctor doctor, Education education);

    /**
     * 更新现有实例
     *
     * @param doctorDTO
     * @param doctor
     */
    @Mappings({
            @Mapping(source = "doctorDTO.specialization", target = "specialty"),
            @Mapping(source = "doctorDTO.patientDTOList", target = "patientList")
    })
    void updateModel(DoctorDTO doctorDTO, @MappingTarget Doctor doctor);
}
