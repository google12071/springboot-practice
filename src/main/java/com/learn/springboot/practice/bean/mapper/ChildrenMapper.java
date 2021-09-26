package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.AChildren;
import com.learn.springboot.practice.bean.mapstruct.BChildren;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author lfq
 */
@Mapper
public interface ChildrenMapper {
    ChildrenMapper INSTANCE = Mappers.getMapper(ChildrenMapper.class);

    @Mappings({
            @Mapping(source = "str", target = "address")
    })
    AChildren toA(BChildren bChildren);
}
