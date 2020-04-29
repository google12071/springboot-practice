package com.learn.springboot.practice.common;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.dao.mybatis.CountryMapper;
import com.learn.springboot.practice.model.Country;
import com.learn.springboot.practice.model.UserInfo;
import com.learn.springboot.practice.service.CompositeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class CommonTests {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CompositeService compositeService;

    @Test
    public void sayHello() {
        log.info("hello");
    }

    @Test
    public void queryAllCountry() {
        List<Country> countryList = countryMapper.getAllCountryList();
        if (CollectionUtils.isNotEmpty(countryList)) {
            countryList.forEach(System.out::println);
        }
    }

    @Test
    public void getUserInfo(){
        UserInfo userInfo = compositeService.getUserInfoById(1);
        log.info("userInfo:{}", userInfo);
    }
}
