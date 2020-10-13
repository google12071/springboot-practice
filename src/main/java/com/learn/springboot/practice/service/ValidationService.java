package com.learn.springboot.practice.service;

import com.learn.springboot.practice.annotation.AopConfig;
import com.learn.springboot.practice.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @ClassName ValidationService
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
@Slf4j
@Service
@Validated
public class ValidationService {

    @AopConfig
    public void validatePerson(@Valid Person person) {
    }
}
