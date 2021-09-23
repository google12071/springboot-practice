package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.AChildren;
import com.learn.springboot.practice.bean.mapstruct.BChildren;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author lfq
 */
@Mapper(componentModel = "spring")
public interface ChildrenMapper {
    @Mappings({
            @Mapping(source = "str", target = "address")
    })
    AChildren toA(BChildren bChildren);
}
