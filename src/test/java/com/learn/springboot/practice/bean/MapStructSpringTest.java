package com.learn.springboot.practice.bean;


import com.learn.springboot.practice.BaseTest;
import com.learn.springboot.practice.bean.mapper.ChildrenMapper;
import com.learn.springboot.practice.bean.mapstruct.AChildren;
import com.learn.springboot.practice.bean.mapstruct.BChildren;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MapStructSpringTest extends BaseTest {
    @Autowired
    private ChildrenMapper childrenMapper;
    @Test
    public void childrenMapper() {
        BChildren bChildren = new BChildren();
        bChildren.setStr("浙江杭州");
        bChildren.setFlag(true);
        bChildren.setId(1);
        bChildren.setName("test");
        AChildren aChildren = childrenMapper.toA(bChildren);
        System.out.println(aChildren);
    }
}
