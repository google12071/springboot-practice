package com.learn.springboot.practice.dp.composite;

public class RewardStrategyClient {
    public static void main(String[] args) {
        RewardStrategyFactory factory = new RewardStrategyFactory();
        //新用户
        RewardStrategy newUserStrategy = factory.createStrategy(NewUserRewardStrategy.class);
        RewardContext newUserContext = new RewardContext(newUserStrategy);
        newUserContext.doStrategy(1);

        //老用户
        RewardStrategy oldUserStrategy = factory.createStrategy(OldUserRewardStrategy.class);
        RewardContext oldUserContext = new RewardContext(oldUserStrategy);
        oldUserContext.doStrategy(2);
    }
}
