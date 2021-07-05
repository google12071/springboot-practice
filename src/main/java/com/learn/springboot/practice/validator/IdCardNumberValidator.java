package com.learn.springboot.practice.validator;

import com.learn.springboot.practice.annotation.IdCardNumber;
import com.learn.springboot.practice.utils.IdCardNumberUtils;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @ClassName IdCardNumberValidator
 * @Description:自定义身份证校验工具
 * @Author lfq
 * @Date 2021/2/8
 **/
@Slf4j
public class IdCardNumberValidator implements ConstraintValidator<IdCardNumber, Object> {
    @Override
    public void initialize(IdCardNumber constraintAnnotation) {
        log.info("initialize....");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Objects.nonNull(value) && IdCardNumberUtils.isIDNumber(value.toString());
    }
}
