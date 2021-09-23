package com.learn.springboot.practice.annotation;

import com.learn.springboot.practice.validator.IdCardNumberValidator;
import com.learn.springboot.practice.validator.SexValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = SexValidator.class)
public @interface Sex {
    String message() default "性别不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
