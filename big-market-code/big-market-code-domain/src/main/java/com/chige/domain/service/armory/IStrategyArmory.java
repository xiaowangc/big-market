package com.chige.domain.service.armory;

/**
 * @Author wangyc
 * @Description 策略工厂类
 * @Date 2024/11/28 18:15
 */
public interface IStrategyArmory {

    /**
     * 策略装配库(兵工厂)，负责初始化策略计算；
     * 1.查询策略配置
     * 2.获取最小概率值
     * 3.获取概率值总和
     * 4.用 1 % 0.0001 获得概率范围，百分位、千分位、万分位
     * 5.生成策略奖品概率查找表「这里指需要在list集合中，存放上对应的奖品占位即可，占位越多等于概率越高」
     * 6.对存储的奖品进行乱序操作。避免顺序生成的随机数前面是固定的奖品。
     * 7.生成出Map集合，key值，对应的就是后续的概率值。通过概率来获得对应的奖品ID
     * 8.存放到 Redis
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * 根据策略Id获取随机奖品id
     */
    Integer getRandomAwardId(Long strategyId);
}
