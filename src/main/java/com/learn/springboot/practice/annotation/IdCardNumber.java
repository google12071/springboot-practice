package com.learn.springboot.practice.annotation;

import com.learn.springboot.practice.validator.IdCardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * ID身份证，用于自定义注解
 * @author lfq
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IdCardNumberValidator.class)
public @interface IdCardNumber {
    String message() default "身份证号码不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

