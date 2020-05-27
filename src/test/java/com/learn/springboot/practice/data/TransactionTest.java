package com.learn.springboot.practice.data;

import com.learn.springboot.practice.SpringBootPracticeApplication;
import com.learn.springboot.practice.model.User;
import com.learn.springboot.practice.service.CompositeService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName TransactionTest
 * @Description:Spring事务管理测试
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
@SpringBootTest(classes = SpringBootPracticeApplication.class)
@RunWith(SpringRunner.class)
public class TransactionTest {
    @Autowired
    private CompositeService compositeService;

    @Test
    public void saveUser() {
        User user1 = new User();
        user1.setName("文学");

        User user2 = new User();
        user2.setName("文学城，创建于1997年，是一家面向海外华人的中文综合网络社区，目前被中国大陆封锁。其服务包括论坛、新闻、博客、群组以及广告，在海外华人中有着很大的影响。其新闻关注中国内外时事、娱乐及社会新闻，地域上主要着眼于美国和中国。新闻多以转发为主。除新闻以外，其论坛和博客在海外华人圈也颇有人气。");

        User user3 = new User();
        user3.setName("测试");

        List<User> userList = Lists.newArrayList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        compositeService.save(userList);
    }

}
