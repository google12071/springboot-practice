package com.learn.springboot.practice.dp.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 老用户发奖策略
 */
@Slf4j
public class OldUserRewardStrategy extends RewardStrategy {

    @Override
    public int reward(long userId) {
        log.info("oldUser invoke,userId:{}", userId);
        return 0;
    }
}
