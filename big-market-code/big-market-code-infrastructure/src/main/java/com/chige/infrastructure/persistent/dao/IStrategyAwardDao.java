package com.chige.infrastructure.persistent.dao;

import com.chige.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wangyc
 * @Description 策略奖品持久层
 * @Date 2024/11/27 22:18
 */
@Mapper
public interface IStrategyAwardDao {

    Integer insert(StrategyAward strategyAward);

    List<StrategyAward> queryStrategyAwardListByStrategyId(@Param("strategyId") Long strategyId);
}
