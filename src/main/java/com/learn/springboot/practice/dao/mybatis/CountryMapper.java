package com.learn.springboot.practice.dao.mybatis;

import com.learn.springboot.practice.model.Country;

import java.util.List;

public interface CountryMapper {
    List<Country> getAllCountryList();
}
