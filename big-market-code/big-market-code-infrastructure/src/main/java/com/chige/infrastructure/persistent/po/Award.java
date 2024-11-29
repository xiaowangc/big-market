package com.chige.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author wangyc
 * @Description 奖品实体类
 * @Date 2024/11/27 22:14
 */
@Data
public class Award {

    private Long id;

    /**
     * 奖品id
     */
    private Integer awardId;

    /**
     * 奖品key
     */
    private String awardKey;

    /**
     * 奖品配置
     */
    private String awardConfig;

    /**
     * 奖品描述
     */
    private String awardDesc;

    private Date createTime;

    private Date updateTime;

}
