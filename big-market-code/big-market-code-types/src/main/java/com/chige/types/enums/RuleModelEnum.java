package com.chige.types.enums;

/**
 * @Author wangyc
 * @Description 规则模型枚举
 * @Date 2024/11/30 12:25
 */
public enum RuleModelEnum {

    RULE_WEIGHT("rule_weight", "规则权重"),


    ;

    public static boolean isRuleWeight(String ruleModel) {
        return RULE_WEIGHT.getRuleModel().equals(ruleModel);
    }

    private String ruleModel;

    private String desc;

    public String getRuleModel() {
        return ruleModel;
    }

    public String getDesc() {
        return desc;
    }

    RuleModelEnum(String ruleModel, String desc) {
        this.ruleModel = ruleModel;
        this.desc = desc;
    }

}
