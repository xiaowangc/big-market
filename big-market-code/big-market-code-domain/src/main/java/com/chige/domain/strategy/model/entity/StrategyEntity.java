package com.chige.domain.strategy.model.entity;

import com.chige.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.chige.types.common.Constants;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author wangyc
 * @Description 策略实体
 * @Date 2024/11/28 18:23
 */
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyEntity {

    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 抽奖规则模型 rule_weight, rule_blacklist */
    private String ruleModels;

    public String[] ruleModels() {
        if (StringUtils.isBlank(ruleModels)) {
            return null;
        }
        return ruleModels.split(Constants.SPLIT);
    }

    /**
     * 获取规则权重
     */
    public String getRuleWeight() {
        String[] ruleModelsArr = this.ruleModels();
        if (ruleModelsArr == null) {
            return null;
        }
        for (String ruleModel : ruleModelsArr) {
            if (DefaultLogicFactory.LogicModel.isRuleWeight(ruleModel)) {
                return ruleModel;
            }
        }
        return null;
    }

}
