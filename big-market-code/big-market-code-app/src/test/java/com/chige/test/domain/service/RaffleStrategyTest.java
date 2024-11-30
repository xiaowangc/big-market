package com.chige.test.domain.service;

import com.alibaba.fastjson.JSON;
import com.chige.domain.strategy.model.entity.RaffleAwardEntity;
import com.chige.domain.strategy.model.entity.RaffleFactorEntity;
import com.chige.domain.strategy.service.IRaffleStrategy;
import com.chige.domain.strategy.service.rule.impl.RuleWeightLogicFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * @Author wangyc
 * @Description 抽奖策略单元测试
 * @Date 2024/11/30 17:42
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private RuleWeightLogicFilter ruleWeightLogicFilter;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 40500L);
    }

    @Test
    public void test_doRaffle() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .strategyId(100001L)
                .userId("xiaowang")
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.doRaffle(raffleFactorEntity);
        log.info("请求参数:{}", JSON.toJSONString(raffleFactorEntity));
        log.info("抽奖结果为：{}", JSON.toJSONString(raffleAwardEntity));
    }

}
