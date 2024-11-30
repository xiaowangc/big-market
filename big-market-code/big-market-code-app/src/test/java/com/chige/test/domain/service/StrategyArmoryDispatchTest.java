package com.chige.test.domain.service;

import com.chige.Application;
import com.chige.domain.strategy.model.entity.StrategyEntity;
import com.chige.domain.strategy.service.armory.IStrategyArmory;
import com.chige.domain.strategy.service.armory.IStrategyDispatch;
import com.chige.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author wangyc
 * @Description 策略领域单元测试
 * @Date 2024/11/29 10:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StrategyArmoryDispatchTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IRedisService redisService;
    @Resource
    private IStrategyDispatch strategyDispatch;

    /**
     * 策略ID；100001L、100002L 装配的时候创建策略表写入到 Redis Map 中
     */
    @Test
    public void test_strategyArmory() {
        boolean b = strategyArmory.assembleLotteryStrategy(100001L);
        log.info("测试结果：{}", b);
    }

    @Test
    public void test_redis() {
        StrategyEntity strategy = new StrategyEntity();
        strategy.setStrategyId(400L);
        strategy.setRuleModels("rule_weight");
        strategy.setStrategyDesc("你是非得失阿道夫");
        redisService.setValue("big_market_strategy_400", strategy);
        StrategyEntity strategyEntity = redisService.getValue("big_market_strategy_400");
        log.info("测试结果：{}", strategyEntity);
    }

    /**
     * 测试抽奖:从装配的策略中随机获取奖品ID值
     */
    @Test
    public void test_getAssembleRandomVal() {
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(100001L));
    }


    @Test
    public void test_shuffle() {
        Map<Integer, Integer> strategyAwardSearchRateTable = new HashMap<>();

        strategyAwardSearchRateTable.put(1, 10);
        strategyAwardSearchRateTable.put(2, 20);
        strategyAwardSearchRateTable.put(3, 30);
        strategyAwardSearchRateTable.put(4, 40);

        List<Integer> valueList = new ArrayList<>(strategyAwardSearchRateTable.values());

        //乱序
        Collections.shuffle(valueList);

        Map<Integer, Integer> randomizedMap = new LinkedHashMap<>();
        Iterator<Integer> valueIterator = valueList.iterator();
        for (Integer key : strategyAwardSearchRateTable.keySet()) {
            randomizedMap.put(key, valueIterator.next());
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : randomizedMap.entrySet()) {
            System.out.println("Key:" + integerIntegerEntry.getKey() + ",value:" + integerIntegerEntry.getValue());
        }
    }

    @Test
    public void test_getRandomAwardId_ruleWeightValue() {

        Integer randomAwardId = strategyDispatch.getRandomAwardId(100001L, "4000:102,103,104,105");
        Integer randomAwardId1 = strategyDispatch.getRandomAwardId(100001L, "5000:102,103,104,105,106,107");
        Integer randomAwardId2 = strategyDispatch.getRandomAwardId(100001L, "6000:102,103,104,105,106,107,108,109");

        log.info("测试结果：4000 - {}", randomAwardId);
        log.info("测试结果：5000 - {}", randomAwardId1);
        log.info("测试结果  6000 - {}", randomAwardId2);

    }

}
