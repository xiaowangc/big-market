package com.chige.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),

    /** 抽奖策略相关 1000-1999*/
    STRATEGY_RULE_WEIGHT_IS_NULL("1000", "策略规则权重不能为空"),

    ;

    private String code;
    private String info;

}
