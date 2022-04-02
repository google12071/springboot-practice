package com.learn.springboot.practice.dp.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lfq
 */
@Slf4j
public class ProductA extends Product {
    @Override
    public void method() {
        log.info("productA");
    }
}
