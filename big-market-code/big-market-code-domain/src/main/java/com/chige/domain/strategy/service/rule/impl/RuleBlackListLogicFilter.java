package com.chige.domain.strategy.service.rule.impl;

import com.alibaba.fastjson.JSON;
import com.chige.domain.strategy.model.entity.RuleActionEntity;
import com.chige.domain.strategy.model.entity.RuleMatterEntity;
import com.chige.domain.strategy.model.entity.StrategyRuleEntity;
import com.chige.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.chige.domain.strategy.repository.IStrategyRepository;
import com.chige.domain.strategy.service.annotation.LogicStrategy;
import com.chige.domain.strategy.service.rule.ILogicFilter;
import com.chige.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.chige.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author wangyc
 * @Description 抽奖规则：黑名单规则
 * @Date 2024/11/30 14:46
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBlackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("黑名单规则过滤:{}", JSON.toJSONString(ruleMatterEntity));
        String userId = ruleMatterEntity.getUserId();
        Long strategyId = ruleMatterEntity.getStrategyId();
        String ruleModel = ruleMatterEntity.getRuleModel();
        Integer awardId = ruleMatterEntity.getAwardId();

        //查询规则配置
        String ruleValue = strategyRepository.queryStrategyRuleValue(strategyId, awardId, ruleModel);
        String[] ruleKeyValue = ruleValue.split(Constants.COLON);
        Integer awardIdStr = Integer.parseInt(ruleKeyValue[0]);
        String[] userIdArray = ruleKeyValue[1].split(",");

        for (String userIdStr : userIdArray) {
            if (userIdStr.equals(userId)) {
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .awardId(awardIdStr)
                                .strategyId(strategyId)
                                .build())
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .build();
            }
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }
}
