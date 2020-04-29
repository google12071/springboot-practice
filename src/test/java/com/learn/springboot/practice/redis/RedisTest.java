package com.learn.springboot.practice.redis;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.service.CompositeService;
import com.learn.springboot.practice.service.UserCacheService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RedisTest
 * @Description:
 * @Author lfq
 * @Date 2020/4/17
 **/

@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@Data
public class RedisTest {
    @Autowired
    private CompositeService compositeService;

    @Autowired
    private UserCacheService cacheService;

    @Resource(name = "jackson2Template")
    private RedisTemplate<String,Object> jackson2RedisTemplate;

    @Resource(name = "genericJackson2Template")
    private RedisTemplate<String, Object> genericJackson2Template;

    @Test
    public void opsRedis() {
        User user = compositeService.getUser(1L);
        log.info("user from db:{}", user);
        String key = "uid." + user.getUid();
        jackson2RedisTemplate.opsForValue().set("uid." + user.getUid(), user);
        user = (User) jackson2RedisTemplate.opsForValue().get(key);
        log.info("user from redis:{}", user);
    }

    @Test
    public void opsHash(){
        List<User> userList = compositeService.getUserList();
        String key="userMap";
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(user -> jackson2RedisTemplate.opsForHash().put(key, String.valueOf(user.getUid()), user));
        }

        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(user -> {
                User var = (User) jackson2RedisTemplate.opsForHash().get(key, String.valueOf(user.getUid()));
                log.info("var:{}", var);
            });
        }
    }

    @Test
    public void opsForList() {
        List<User> userList = compositeService.getUserList();
        String listKey = "userList";
        if (CollectionUtils.isNotEmpty(userList)) {
            jackson2RedisTemplate.opsForValue().set(listKey, userList);
        }

        List<User> list = (List<User>) jackson2RedisTemplate.opsForValue().get(listKey);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    @Test
    public void getUser() {
        User user = cacheService.getUser(1L);
        log.info("user:{}", user);
    }

    @Test
    public void getUserFromMultiCache(){
        User user = cacheService.getUserFromMultiCache(1L);
        log.info("user:{}", user);
    }
}
