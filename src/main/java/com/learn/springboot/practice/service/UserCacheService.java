package com.learn.springboot.practice.service;

import com.learn.springboot.practice.dao.learn.UserMapper;
import com.learn.springboot.practice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserCacheService
 * @Description:
 * @Author lfq
 * @Date 2020/4/18
 **/
@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class UserCacheService {
    @Autowired
    private UserMapper userMapper;

    @CachePut(key = "#user.getUid()", condition = "#user.getUid()>0", cacheManager = "redisCaffeineCacheManager")
    public User save(User user) {
        Long uid = userMapper.saveUser(user);
        if (uid == 0) {
            throw new RuntimeException("保存用户失败");
        }
        return userMapper.getUserById(user.getUid());
    }

    @CachePut(key = "#uid", cacheManager = "redisCaffeineCacheManager")
    public User update(String name, String address, Long uid) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setUid(uid);
        userMapper.updateUser(user);
        return user;
    }

    @CacheEvict(key = "#uid", beforeInvocation = true, cacheManager = "redisCaffeineCacheManager")
    public void delete(Long uid) {
        userMapper.deleteUser(uid);
    }

    @Cacheable(key = "#uid",cacheManager = "redisCaffeineCacheManager")
    public User getUser(Long uid) {
        log.info("getUser no cache,uid:{}", uid);
        return userMapper.getUserById(uid);
    }


    @Cacheable(key = "#uid", cacheManager = "redisCaffeineCacheManager")
    public User getUserFromMultiCache(Long uid) {
        log.info("getUser from db,uid:{}", uid);
        return userMapper.getUserById(uid);
    }
}
