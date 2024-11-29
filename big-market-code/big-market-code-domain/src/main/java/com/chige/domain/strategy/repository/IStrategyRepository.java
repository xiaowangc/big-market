package com.chige.domain.strategy.repository;

import com.chige.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author wangyc
 * @Description 定义策略仓储层
 * @Date 2024/11/28 18:19
 */
public interface IStrategyRepository {
    /**
     * 根据策略ID查询策略奖品
     * @param strategyId
     * @return
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储策略奖品概率区间
     * @param strategyId 策略ID
     * @param rateRange 概率区间
     * @param shuffleStrategyAwardSearchRateTable
     */
    void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Integer> shuffleStrategyAwardSearchRateTable);

    /**
     * 根据策略ID查询策略奖品概率区间
     * @param strategyId
     * @return
     */
    int getRateRange(Long strategyId);

    /**
     * 根据策略ID查询策略奖品概率区间
     * @param strategyId 策略id
     * @param rateKey 概率key
     * @return
     */
    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
