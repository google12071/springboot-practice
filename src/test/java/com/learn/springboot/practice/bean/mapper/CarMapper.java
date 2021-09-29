package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.pojo.Car;
import com.learn.springboot.practice.pojo.CarDTO;
import com.learn.springboot.practice.pojo.CarType;
import com.learn.springboot.practice.utils.GsonUtil;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type", target = "type", qualifiedByName = "toCarType"),
            @Mapping(source = "num", target = "price", qualifiedByName = "getCallPrice")
    })
    CarDTO car2CarDTO(Car car);

    List<CarDTO> carList2CarDTOList(List<Car> carList);


    @InheritInverseConfiguration(name = "car2CarDTO")
    @Mappings({
            @Mapping(source = "price", target = "num", qualifiedByName = "getNum"),
            @Mapping(source = "type", target = "type", qualifiedByName = "toCarType")
    })
    Car dto2Car(CarDTO carDTO);

    List<Car> dtoList2CarList(List<CarDTO> carDTOList);

    @Named("getCallPrice")
    static BigDecimal getCallPrice(int num) {
        return new BigDecimal(num * 100);
    }

    @Named("getNum")
    static Integer getNum(BigDecimal bigDecimal) {
        return bigDecimal.divide(new BigDecimal(100)).intValue();
    }

    @Named("toCarType")
    static CarType toCarType(String type) {
        return GsonUtil.toBean(type, CarType.class);
    }

    @Named("toCarType")
    static String toCarType(CarType type) {
        return GsonUtil.toString(type);
    }
}
