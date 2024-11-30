package com.chige.domain.strategy.repository;

import com.chige.domain.strategy.model.entity.StrategyAwardEntity;
import com.chige.domain.strategy.model.entity.StrategyEntity;
import com.chige.domain.strategy.model.entity.StrategyRuleEntity;

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
    void storeStrategyAwardSearchRateTable(String strategyId, Integer rateRange, Map<Integer, Integer> shuffleStrategyAwardSearchRateTable);

    /**
     * 根据策略ID查询策略奖品概率区间
     * @param strategyId
     * @return
     */
    int getRateRange(Long strategyId);

    int getRateRange(String key);

    /**
     * 根据策略ID查询策略奖品概率区间
     * @param strategyId 策略id
     * @param rateKey 概率key
     * @return
     */
    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);

    /**
     * 根据策略id查询策略信息实体
     * @param strategyId 策略id
     * @return 策略实体
     */
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    /**
     * 根据策略Id,规则模型查询策略规则实体
     * @param strategyId 策略id
     * @param ruleModel 规则模型
     * @return 策略规则实体
     */
    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

}
