package com.learn.springboot.practice.dp.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 新用户发奖
 */
@Slf4j
public class NewUserRewardStrategy extends RewardStrategy {
    @Override
    public int reward(long userId) {
        log.info("newUser invoke,userId:{}", userId);
        return 0;
    }
}
