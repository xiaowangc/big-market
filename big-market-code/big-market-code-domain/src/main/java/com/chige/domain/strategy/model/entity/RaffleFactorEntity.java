package com.chige.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangyc
 * @Description 抽奖因子实体
 * @Date 2024/11/30 15:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 抽奖策略
     */
    private Long strategyId;

    /**
     * 奖品id
     */
    private Integer awardId;

}
