package com.chige.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author wangyc
 * @Description 策略表
 * @Date 2024/11/27 22:17
 */
@Data
public class Strategy {
    private Long id;

    /**
     * 策略id
     */
    private Long strategyId;

    /**
     * 策略描述
     */
    private String strategyDesc;

    /**
     * 规则模型
     */
    private String ruleModels;

    private Date createTime;

    private Date updateTime;
}
