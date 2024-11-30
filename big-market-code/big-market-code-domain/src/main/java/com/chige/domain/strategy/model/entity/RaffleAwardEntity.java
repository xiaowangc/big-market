package com.chige.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangyc
 * @Description 抽奖奖品实体
 * @Date 2024/11/30 15:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {

    /**
     * 抽奖策略id
     */
    private Long strategyId;

    /**
     * 奖品id
     */
    private Integer awardId;

    /**
     * 奖品对接标识
     */
    private String awardKey;

    /**
     * 奖品配置信息
     */
    private String awardConfig;

    /**
     * 奖品内容描述
     */
    private String awardDesc;
}
