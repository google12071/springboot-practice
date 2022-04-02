package com.learn.springboot.practice.dp.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lfq
 */
@Slf4j
public class ConcreteFactory extends Factory {
    @Override
    public Product createProduct(Class aClass) {
        Product product = null;
        try {
            product = (Product) Class.forName(aClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
