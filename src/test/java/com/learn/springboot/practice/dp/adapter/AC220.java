package com.learn.springboot.practice.dp.adapter;

public class AC220 implements AC {
    private final int output = 220;

    @Override
    public int outputAC() {
        return output;
    }
}
