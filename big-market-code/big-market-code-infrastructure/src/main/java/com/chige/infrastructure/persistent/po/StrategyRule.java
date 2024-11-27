package com.chige.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author wangyc
 * @Description 策略规则表
 * @Date 2024/11/27 22:17
 */
@Data
public class StrategyRule {

    private Long id;

    /**
     * 抽奖策略ID
     */
    private Integer strategyId;

    /**
     * 抽奖奖品ID【规则类型为策略，则不需要奖品ID】
     */
    private Integer awardId;

    /**
     * 抽象规则类型；1-策略规则、2-奖品规则
     */
    private Integer ruleType;

    /**
     * 抽奖规则比值
     */
    private String ruleValue;

    /**
     * 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】
     */
    private String ruleModel;

    /**
     * 抽奖规则描述
     */
    private String ruleDesc;

    private Date createTime;

    private Date updateTime;

}
