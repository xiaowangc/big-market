package com.chige.test.domain.service;

import com.alibaba.fastjson.JSON;
import com.chige.domain.strategy.model.entity.RaffleAwardEntity;
import com.chige.domain.strategy.model.entity.RaffleFactorEntity;
import com.chige.domain.strategy.service.IRaffleStrategy;
import com.chige.domain.strategy.service.armory.IStrategyArmory;
import com.chige.domain.strategy.service.armory.StrategyArmoryDispatch;
import com.chige.domain.strategy.service.rule.impl.RuleLockLogicFilter;
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
    @Resource
    private RuleLockLogicFilter ruleLockLogicFilter;
    @Resource
    private IStrategyArmory strategyArmory;

    @Before
    public void setUp() {
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100001L));
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100002L));
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100003L));

        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 40500L);
        ReflectionTestUtils.setField(ruleLockLogicFilter, "userRaffleCount", 6L);
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

    @Test
    public void test_raffle_center_rule_lock(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("xiaofuge")
                .strategyId(100003L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.doRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

}
