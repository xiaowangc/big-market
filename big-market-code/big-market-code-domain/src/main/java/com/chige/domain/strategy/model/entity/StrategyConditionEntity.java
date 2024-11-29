package com.chige.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangyc
 * @Description 策略条件实体
 * @Date 2024/11/28 18:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyConditionEntity {

    /** 用户id */
    private String userId;
    /** 策略id */
    private Long strategyId;

}
