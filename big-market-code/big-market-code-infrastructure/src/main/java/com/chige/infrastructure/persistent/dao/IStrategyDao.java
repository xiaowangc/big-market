package com.chige.infrastructure.persistent.dao;

import com.chige.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author wangyc
 * @Description 策略持久层
 * @Date 2024/11/27 22:18
 */
@Mapper
public interface IStrategyDao {

    Integer insert(Strategy strategy);

}
