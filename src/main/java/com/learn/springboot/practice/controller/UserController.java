package com.learn.springboot.practice.controller;

import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description:
 * @Author lfq
 * @Date 2020/4/14
 **/
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserCacheService cacheService;

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public User getUserByUid(@PathVariable Long uid) {
        return cacheService.getUser(uid);
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return cacheService.save(user);
    }

    @RequestMapping(value = "/user/delete/{uid}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long uid) {
        cacheService.delete(uid);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return cacheService.update(user.getName(), user.getAddress(), user.getUid());
    }
}
