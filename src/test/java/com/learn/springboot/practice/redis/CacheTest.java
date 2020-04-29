package com.learn.springboot.practice.redis;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName CacheTest
 * @Description:
 * @Author lfq
 * @Date 2020/4/18
 **/

@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class CacheTest {
    @Autowired
    UserCacheService cacheService;

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("张小屋");
        user.setMoney(25000.00);
        user.setAge(23);
        user.setDes("高级架构工程师");
        user.setAddress("安徽省合肥市");
        User result = cacheService.save(user);
        log.info("user:{}", result);
    }

    @Test
    public void deleteUser() {
        cacheService.delete(23L);
    }

    @Test
    public void updateUser() {
        cacheService.update("测试人员", "河南省郑州市", 13L);
    }

    @Test
    public void queryUser() {
        User user = cacheService.getUser(23L);
        if (user != null) {
            log.info("user:{}", user);
        }
    }
}
