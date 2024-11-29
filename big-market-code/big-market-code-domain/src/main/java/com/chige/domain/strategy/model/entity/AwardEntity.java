package com.chige.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangyc
 * @Description 策略结果实体
 * @Date 2024/11/28 18:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardEntity {

    /** 用户ID */
    private String userId;
    /** 奖品ID */
    private Integer awardId;

}
