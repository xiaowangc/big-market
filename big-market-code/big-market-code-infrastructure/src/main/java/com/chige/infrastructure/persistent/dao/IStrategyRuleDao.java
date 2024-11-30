package com.chige.infrastructure.persistent.dao;

import com.chige.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author wangyc
 * @Description 策略规则持久层
 * @Date 2024/11/27 22:18
 */
@Mapper
public interface IStrategyRuleDao {

    Integer insert(StrategyRule strategyRule);

    StrategyRule queryStrategyRule(StrategyRule strategyRule);

}
