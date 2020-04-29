package com.learn.springboot.practice.service;

import com.learn.springboot.practice.dao.learn.UserMapper;
import com.learn.springboot.practice.dao.mybatis.CountryMapper;
import com.learn.springboot.practice.dao.mybatis.UserInfoMapper;
import com.learn.springboot.practice.model.Country;
import com.learn.springboot.practice.model.IpInfo;
import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.dao.learn.IpInfoMapper;
import com.learn.springboot.practice.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CompositeService
 * @Description:
 * @Author lfq
 * @Date 2020/4/14
 **/
@Service
public class CompositeService {
    @Autowired
    private IpInfoMapper ipInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<IpInfo> getIpInfo() {
        return ipInfoMapper.getAllIpInfoList();
    }

    /**
     * @param uid
     * @return
     */
    public User getUser(Long uid) {
        return userMapper.getUserById(uid);
    }

    public List<User> getUserList() {
        return userMapper.getAllUser();
    }

    public List<Country> getCountryList() {
        return countryMapper.getAllCountryList();
    }

    public UserInfo getUserInfoById(Integer uid){
        return userInfoMapper.getUserInfo(uid);
    }
}

