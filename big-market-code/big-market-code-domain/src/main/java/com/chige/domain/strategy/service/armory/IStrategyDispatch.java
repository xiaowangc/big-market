package com.chige.domain.strategy.service.armory;

/**
 * @Author wangyc
 * @Description 策略抽奖调度
 * @Date 2024/11/29 18:30
 */
public interface IStrategyDispatch {

    /**
     * 获取抽奖策略装配的随机结果
     * @param strategyId 策略id
     * @return 抽奖结果标识
     */
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);


}
