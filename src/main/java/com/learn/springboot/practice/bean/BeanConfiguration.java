package com.learn.springboot.practice.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


/**
 * Spring条件装配，在ABean存在的情况下，装配BBean
 *
 * @author lfq
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ABean aBean() {
        return new ABean("A");
    }

    /**
     * BeanCondition matches方法返回true时装配Bean,当ABean存在时，装配BBean
     *
     * @return
     */
    @Bean
    @Conditional(BeanCondition.class)
    public BBean bBean() {
        return new BBean("B");
    }
}
