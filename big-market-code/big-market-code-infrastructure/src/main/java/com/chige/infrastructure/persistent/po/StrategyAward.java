package com.chige.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author wangyc
 * @Description 策略奖品表
 * @Date 2024/11/27 22:17
 */
@Data
public class StrategyAward {

    private Long id;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;


    /**
     * 抽奖奖品id 内部id
     */
    private Integer awardId;

    /**
     * 抽奖奖品标题
     */
    private String awardTitle;

    /**
     * 抽奖奖品副标题
     */
    private String awardSubtitle;

    /**
     * 奖品总库存
     */
    private Integer awardCount;

    /**
     * 奖品库存剩余
     */
    private Integer awardCountSurplus;

    /**
     * 奖品概率
     */
    private Double awardRate;

    /**
     * 规则模型，rule配置的模型同步到此表，便于使用
     */
    private String ruleModels;

    /**
     * 排序
     */
    private Integer sort;

    private Date createTime;

    private Date updateTime;




}
