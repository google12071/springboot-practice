package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.Patient;
import com.learn.springboot.practice.bean.mapstruct.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author lfq
 */
@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(source = "patientName", target = "name")
    })
    PatientDTO toPatientDto(Patient patient);


    @Mappings({
            @Mapping(source = "name", target = "patientName")
    })
    Patient toPatient(PatientDTO patientDTO);
}
