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

    private String strategyId;

    private String strategyDesc;

    private Date createTime;

    private Date updateTime;
}