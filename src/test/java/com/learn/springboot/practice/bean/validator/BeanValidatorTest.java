package com.learn.springboot.practice.bean.validator;

import com.learn.springboot.practice.BaseTest;
import com.learn.springboot.practice.validator.BizValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean参数校验测试类
 */
@Slf4j
public class BeanValidatorTest extends BaseTest {
    @Autowired
    private BizValidatorService validatorService;

    @Test
    public void saveModel() {
        ValidatorModel model = new ValidatorModel();
        model.setUserName("张三");
        model.setAge(29);
        model.setIdCardNumber("342224199311050316");
        model.setIsFalse(false);

        RelevanceModel relevanceModel=new RelevanceModel();
        model.setRelevanceModel(relevanceModel);
        try {
            validatorService.saveModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryById() {
        ValidatorModel model = validatorService.queryById(null);
        log.info("model:{}", model);
    }
}
