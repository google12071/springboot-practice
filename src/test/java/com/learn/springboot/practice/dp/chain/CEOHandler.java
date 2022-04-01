package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CEOHandler implements ChainHandler {
    @Override
    public Boolean execute(ChainRequest request) {
        return true;
    }
}
