package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.pojo.Boss;
import com.learn.springboot.practice.pojo.BossDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface BossMapper {
    @Mappings({
            @Mapping(target = "company", source = "companyName"),
            @Mapping(target = "carDTOList", source = "carList")
    })
    BossDTO toBossDTO(Boss boss);

    List<BossDTO> toBossDTOList(List<Boss> bossList);
}
