package com.learn.springboot.practice.validate;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.pojo.Person;
import com.learn.springboot.practice.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @ClassName ValidateService
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Test
    public void checkUser() {
        validationService.validatePerson(builderPerson());
    }

    public Person builderPerson() {
        Person person = new Person();
        person.setPersonId(1L);
        person.setAge(120);
        person.setEmail("fqqq.com");
        person.setName("fq");
        person.setSex("男");
        return person;
    }
}
