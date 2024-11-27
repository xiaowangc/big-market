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

    private Integer awardId;

    private String awardKey;

    private String awardConfig;

    private String awardDesc;

    private Date createTime;

    private Date updateTime;

}
