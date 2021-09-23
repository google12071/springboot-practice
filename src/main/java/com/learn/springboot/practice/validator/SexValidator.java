package com.learn.springboot.practice.validator;

import com.learn.springboot.practice.annotation.Sex;
import com.learn.springboot.practice.model.constant.SexEnum;
import lombok.extern.slf4j.Slf4j;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lfq
 */
@Slf4j
public class SexValidator implements ConstraintValidator<Sex, Object> {
    @Override
    public void initialize(Sex constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value instanceof SexEnum;
    }
}
