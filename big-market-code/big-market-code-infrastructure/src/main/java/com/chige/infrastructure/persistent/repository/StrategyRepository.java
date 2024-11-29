package com.chige.infrastructure.persistent.repository;

import com.chige.domain.strategy.model.entity.StrategyAwardEntity;
import com.chige.domain.strategy.repository.IStrategyRepository;
import com.chige.infrastructure.persistent.dao.IStrategyAwardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyc
 * @Description 策略仓储层实现类
 * @Date 2024/11/28 18:22
 */
@Slf4j
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        return null;
    }

    @Override
    public void storeStrategyAwardSearchRateTable(Long strategyId, int size, Map<Integer, Integer> shuffleStrategyAwardSearchRateTable) {

    }

    @Override
    public int getRateRange(Long strategyId) {
        return 0;
    }

    @Override
    public Integer getStrategyAwardAssemble(Long strategyId, int rateRange) {
        return null;
    }
}
