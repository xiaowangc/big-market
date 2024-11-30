package com.chige.domain.strategy.model.entity;

import com.chige.types.common.Constants;
import com.chige.types.enums.RuleModelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
 * @Author wangyc
 * @Description 策略规则实体
 * @Date 2024/11/28 18:23
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    /** 抽奖策略ID */
    private Long strategyId;

    /** 抽奖奖品ID */
    private Long awardId;

    /** 规则类型 1-策略规则 2-奖品规则 */
    private Integer ruleType;

    /** 抽奖规则模型 【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleMode;

    /** 抽奖规则比值 */
    private String ruleValue;

    /** 抽奖规则描述 */
    private String ruleDesc;

    /**
     * 获取权重值，数据之间用空格隔开
     * 例如：4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     */
    public Map<String, List<Integer>> getRuleWeightValues() {
        if (!RuleModelEnum.isRuleWeight(ruleMode)) return Collections.emptyMap();

        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) {
                return resultMap;
            }
            //分割字符串以获取键和值
            String[] keyAndValue = ruleValueGroup.split(Constants.COLON);
            if (keyAndValue.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }


            // 解析值
            String[] valueStrings = keyAndValue[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for (String valueString : valueStrings) {
                values.add(Integer.parseInt(valueString));
            }
            resultMap.put(ruleValueGroup, values);
        }

        return resultMap;
    }
}
