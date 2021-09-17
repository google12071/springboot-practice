package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.Patient;
import com.learn.springboot.practice.bean.mapstruct.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lfq
 */
@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(source = "patientName", target = "name"),
            @Mapping(source = "birthDay", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    })
    PatientDTO toPatientDto(Patient patient);


    @Mappings({
            @Mapping(source = "name", target = "patientName"),
            @Mapping(source = "dateOfBirth", target = "birthDay", dateFormat = "yyyy-MM-dd")
    })
    Patient toPatient(PatientDTO patientDTO);

    /*******List映射*********/
    List<Patient> toPatientList(List<PatientDTO> patientDTOList);

    List<PatientDTO> toPatientDTOList(List<Patient> patientList);
}
