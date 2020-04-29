package com.learn.springboot.practice.dao.learn;

import com.learn.springboot.practice.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description:
 * @Author lfq
 * @Date 2020/4/14
 **/
public interface UserMapper {
    List<User> getAllUser();

    User getUserById(@Param("uid") Long uid);

    Integer updateUser(User user);

    Long saveUser(User user);

    Integer deleteUser(@Param("uid") Long uid);
}
