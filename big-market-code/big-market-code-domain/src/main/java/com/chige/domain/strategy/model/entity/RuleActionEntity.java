package com.chige.domain.strategy.model.entity;

import com.chige.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @Author wangyc
 * @Description 规则动作实体
 * @Date 2024/11/30 14:47
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;



    public static class RaffleEntity {

    }

    /**
     * 抽奖前
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RaffleBeforeEntity extends RaffleEntity {
        /**
         * 抽奖策略id
         */
        private Long strategyId;

        /**
         * 权重值Key；用于抽奖时可以选择权重抽奖。
         */
        private String ruleWeightValueKey;

        /**
         * 奖品ID；
         */
        private Integer awardId;
    }

    /**
     * 抽奖中
     */
    public static class RaffleCenterEntity extends RaffleEntity {

    }


    /**
     * 抽奖后
     */
    public static class RaffleAfterEntity extends RaffleEntity {

    }



}
