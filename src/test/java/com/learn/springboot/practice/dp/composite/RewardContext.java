package com.learn.springboot.practice.dp.composite;

public class RewardContext {
    private RewardStrategy strategy;

    public RewardContext(RewardStrategy strategy) {
        this.strategy = strategy;
    }
    public void doStrategy(long userId) {
        int rewardMoney = strategy.reward(userId);
        strategy.insertRewardAndSettlement(userId,rewardMoney);
    }
}
