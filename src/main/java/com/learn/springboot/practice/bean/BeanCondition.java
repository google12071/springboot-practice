package com.learn.springboot.practice.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义Bean装配条件，matches方法返回true时，条件成立，使用改条件的Bean完成注入
 *
 * @author lfq
 */
@Slf4j
public class BeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        if (factory == null) {
            return false;
        }
        String[] aBeanNames = factory.getBeanNamesForType(ABean.class);
        return aBeanNames.length > 0;
    }
}
