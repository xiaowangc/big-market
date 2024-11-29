package com.chige.test.domain.service;

import com.chige.Application;
import com.chige.domain.service.armory.IStrategyArmory;
import com.chige.domain.service.armory.StrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author wangyc
 * @Description 策略工厂单元测试
 * @Date 2024/11/29 10:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    /**
     * 策略ID；100001L、100002L 装配的时候创建策略表写入到 Redis Map 中
     */
    @Test
    public void test_strategyArmory() {
        boolean b = strategyArmory.assembleLotteryStrategy(100002L);
        log.info("测试结果：{}", b);
    }

    /**
     * 测试抽奖:从装配的策略中随机获取奖品ID值
     */
    @Test
    public void test_getAssembleRandomVal() {
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
    }

}
