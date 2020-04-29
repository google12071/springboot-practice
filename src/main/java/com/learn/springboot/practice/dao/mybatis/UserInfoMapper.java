package com.learn.springboot.practice.dao.mybatis;

import com.learn.springboot.practice.model.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author lfq
 */
public interface UserInfoMapper {
    UserInfo getUserInfo(@Param("id") Integer id);
}
