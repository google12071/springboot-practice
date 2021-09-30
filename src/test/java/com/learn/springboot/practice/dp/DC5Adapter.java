package com.learn.springboot.practice.dp;

public interface DC5Adapter {
    boolean support(AC ac);

    int outputDC5V(AC ac);
}
