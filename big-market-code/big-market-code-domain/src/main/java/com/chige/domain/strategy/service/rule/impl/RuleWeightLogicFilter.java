package com.chige.domain.strategy.service.rule.impl;

import com.chige.domain.strategy.model.entity.RuleActionEntity;
import com.chige.domain.strategy.model.entity.RuleMatterEntity;
import com.chige.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.chige.domain.strategy.repository.IStrategyRepository;
import com.chige.domain.strategy.service.annotation.LogicStrategy;
import com.chige.domain.strategy.service.rule.ILogicFilter;
import com.chige.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.chige.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author wangyc
 * @Description 抽奖规则：权重规则
 * @Date 2024/11/30 14:46
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_WEIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository strategyRepository;
    private Long userScore = 4500L;

    /**
     * 权重规则过滤；
     * 1. 权重规则格式；4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     * 2. 解析数据格式；判断哪个范围符合用户的特定抽奖范围
     *
     * @param ruleMatterEntity 规则物料实体对象
     * @return 规则过滤结果
     */
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {

        Integer awardId = ruleMatterEntity.getAwardId();
        Long strategyId = ruleMatterEntity.getStrategyId();
        String ruleModel = ruleMatterEntity.getRuleModel();

        String ruleValue = strategyRepository.queryStrategyRuleValue(strategyId, awardId, ruleModel);

        // 1. 根据用户ID查询用户抽奖消耗的积分值，本章节我们先写死为固定的值。后续需要从数据库中查询。
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if (null == analyticalValueGroup || analyticalValueGroup.isEmpty()) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 2. 转换Keys值，并默认排序
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);

        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore >= key)
                .max(Long::compareTo)
                .orElse(null);

        if (null != nextValue) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode())
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(strategyId)
                            .ruleWeightValueKey(analyticalValueGroup.get(nextValue))
                            .build())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }


    /**
     * 入参："4000:102,103 5000:104,105"
     * 返回：{4000:"4000:102,103", 5000:"5000:104,105"}
     * @param ruleValue 策略规则值
     */
    private Map<Long, String> getAnalyticalValue(String ruleValue) {
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {

            // 检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) {
                return ruleValueMap;
            }

            String[] ruleKeyValue = ruleValueGroup.split(Constants.COLON);
            if (ruleKeyValue.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + Arrays.toString(ruleKeyValue));
            }

            ruleValueMap.put(Long.parseLong(ruleKeyValue[0]), ruleValueGroup);
        }

        return ruleValueMap;
    }
}
