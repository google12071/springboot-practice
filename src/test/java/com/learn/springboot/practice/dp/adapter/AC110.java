package com.learn.springboot.practice.dp.adapter;

public class AC110 implements AC {
    private final int output = 110;

    @Override
    public int outputAC() {
        return output;
    }
}
