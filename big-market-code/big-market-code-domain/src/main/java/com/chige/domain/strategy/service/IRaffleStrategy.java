package com.chige.domain.strategy.service;

import com.chige.domain.strategy.model.entity.RaffleAwardEntity;
import com.chige.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @Author wangyc
 * @Description 抽奖策略接口
 * @Date 2024/11/30 14:41
 */
public interface IRaffleStrategy {

    /**
     * 执行抽奖；用抽奖因子入参，执行抽奖计算，返回奖品信息
     * @param raffleFactorEntity 抽奖因子
     * @return 抽奖奖品信息
     */
    RaffleAwardEntity doRaffle(RaffleFactorEntity raffleFactorEntity);
}
