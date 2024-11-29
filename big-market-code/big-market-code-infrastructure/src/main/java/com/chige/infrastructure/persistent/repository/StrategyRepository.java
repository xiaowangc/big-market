package com.chige.infrastructure.persistent.repository;

import com.alibaba.fastjson.JSON;
import com.chige.domain.strategy.model.entity.StrategyAwardEntity;
import com.chige.domain.strategy.model.entity.StrategyEntity;
import com.chige.domain.strategy.model.entity.StrategyRuleEntity;
import com.chige.domain.strategy.repository.IStrategyRepository;
import com.chige.infrastructure.persistent.dao.IStrategyAwardDao;
import com.chige.infrastructure.persistent.dao.IStrategyDao;
import com.chige.infrastructure.persistent.po.Strategy;
import com.chige.infrastructure.persistent.po.StrategyAward;
import com.chige.infrastructure.redis.IRedisService;
import com.chige.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Resource
    private IRedisService redisService;
    @Resource
    private IStrategyDao strategyDao;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        //1. 从缓存中获取
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = redisService.getValue(cacheKey);
        if (!CollectionUtils.isEmpty(strategyAwardEntityList)) {
            return strategyAwardEntityList;
        }
        //2. 从库中获取数据
        List<StrategyAward> strategyAwardList = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntityList = new ArrayList<>(strategyAwardList.size());

        for (StrategyAward strategyAward : strategyAwardList) {
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                    .strategyId(strategyAward.getStrategyId())
                    .awardId(strategyAward.getAwardId())
                    .awardCount(strategyAward.getAwardCount())
                    .awardCountSurplus(strategyAward.getAwardCountSurplus())
                    .awardRate(strategyAward.getAwardRate())
                    .build();
            strategyAwardEntityList.add(strategyAwardEntity);
        }
        redisService.setValue(cacheKey, strategyAwardEntityList);
        return strategyAwardEntityList;
    }

    @Override
    public void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Integer> shuffleStrategyAwardSearchRateTable) {
        //1. 存储抽奖策略范围值，如1000，用于生成1000以内的随机数
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId, rateRange);

        //2. 存储概率查找表
        RMap<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId);
        cacheRateTable.putAll(shuffleStrategyAwardSearchRateTable);

    }

    @Override
    public int getRateRange(Long strategyId) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId);
    }

    @Override
    public int getRateRange(String key) {
        return 0;
    }

    @Override
    public Integer getStrategyAwardAssemble(Long strategyId, int rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId, rateKey);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if (Objects.nonNull(strategyEntity)) {
            return strategyEntity;
        }

        Strategy strategy = strategyDao.queryStrategyByStrategyId(strategyId);
        if (Objects.isNull(strategy)) {
            return null;
        }

        strategyEntity = StrategyEntity.builder()
                .strategyId(strategy.getStrategyId())
                .strategyDesc(strategy.getStrategyDesc())
                .ruleModels(strategy.getStrategyDesc())
                .build();
        redisService.setValue(cacheKey, strategyEntity);
        log.info("设置缓存key:{}, value:{}", cacheKey, JSON.toJSONString(strategyEntity));
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        return null;
    }
}
