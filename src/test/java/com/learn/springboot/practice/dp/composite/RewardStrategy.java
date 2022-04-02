package com.learn.springboot.practice.dp.composite;

import lombok.extern.slf4j.Slf4j;

/***
 * 发奖策略
 */
@Slf4j
public abstract class RewardStrategy {
    /**
     * 返回发奖策略
     *
     * @param userId
     */
    public abstract int reward(long userId);

    /**
     * 更新用户信息以及结算
     *
     * @param userId
     * @param reward
     */
    public void insertRewardAndSettlement(long userId, int reward) {
        log.info("insertRewardAndSettlement,userId:{},reward:{}", userId, reward);
    }

}
