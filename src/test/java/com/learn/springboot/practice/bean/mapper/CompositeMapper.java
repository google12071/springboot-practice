package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.pojo.BossDTO;
import com.learn.springboot.practice.pojo.CarDTO;
import com.learn.springboot.practice.pojo.CompositeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        BossMapper.class
})
public interface CompositeMapper {

    /**
     * 同名字段自动映射
     *
     * @param bossDTO
     * @param carDTO
     * @return
     */
    CompositeDTO toComposite(BossDTO bossDTO, CarDTO carDTO);

    /**
     * 对象更新
     *
     * @param message
     * @param compositeDTO
     */

    @Mappings({
            @Mapping(source = "message", target = "compositeDTO.composite")
    })
    void updateComposite(String message, @MappingTarget CompositeDTO compositeDTO);
}
