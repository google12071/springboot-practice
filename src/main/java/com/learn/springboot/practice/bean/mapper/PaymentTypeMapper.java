package com.learn.springboot.practice.bean.mapper;

import com.learn.springboot.practice.bean.mapstruct.PaymentTypeEnum;
import com.learn.springboot.practice.bean.mapstruct.PaymentTypeViewEnum;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentTypeMapper {
    PaymentTypeMapper INSTANCE = Mappers.getMapper(PaymentTypeMapper.class);

    @ValueMappings({
            @ValueMapping(source = "CHEQUE", target = "CHEQUE"),
            @ValueMapping(source = "CARD_CREDIT", target = "CASH"),
            @ValueMapping(source = MappingConstants.ANY_REMAINING, target = "CARD")

    })
    PaymentTypeViewEnum paymentTypeToPaymentTypeView(PaymentTypeEnum paymentTypeEnum);
}
