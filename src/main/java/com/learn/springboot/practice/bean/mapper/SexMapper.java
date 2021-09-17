package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.GenderEnum;
import com.learn.springboot.practice.bean.mapstruct.SexEnum;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SexMapper {
    SexMapper INSTANCE = Mappers.getMapper(SexMapper.class);

    @ValueMappings({
            @ValueMapping(source = "MALE", target = "MEN"),
            @ValueMapping(source = "FEMALE", target = "WOMEN"),
    })
    SexEnum toSex(GenderEnum genderEnum);

    @ValueMappings({
            @ValueMapping(source = "MEN", target = "MALE"),
            @ValueMapping(source = "WOMEN", target = "FEMALE"),
    })
    GenderEnum toGender(SexEnum sexEnum);
}
