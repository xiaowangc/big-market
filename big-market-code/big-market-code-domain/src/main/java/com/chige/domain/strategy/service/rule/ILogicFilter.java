package com.chige.domain.strategy.service.rule;

import com.chige.domain.strategy.model.entity.RuleActionEntity;
import com.chige.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @Author wangyc
 * @Description 抽奖规则过滤器
 * @Date 2024/11/30 14:44
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);

}
