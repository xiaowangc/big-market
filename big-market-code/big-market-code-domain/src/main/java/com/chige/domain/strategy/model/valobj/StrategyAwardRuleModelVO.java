package com.chige.domain.strategy.model.valobj;

import com.chige.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.chige.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyc
 * @Description 抽奖策略规则规则值对象；值对象，没有唯一ID，仅限于从数据库查询对象
 * @Date 2024/11/30 18:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;


    public String[] raffleCenterRuleModelList() {
        List<String> ruleList = new ArrayList<>();
        String[] ruleModelArr = ruleModels.split(Constants.SPLIT);
        for (String ruleMode : ruleModelArr) {
            if (DefaultLogicFactory.LogicModel.isCenter(ruleMode)) {
                ruleList.add(ruleMode);
            }
        }
        return ruleList.toArray(new String[0]);
    }

    public String[] raffleAfterRuleModelList() {
        List<String> ruleList = new ArrayList<>();
        String[] ruleModelArr = ruleModels.split(Constants.SPLIT);
        for (String ruleMode : ruleModelArr) {
            if (DefaultLogicFactory.LogicModel.isAfter(ruleMode)) {
                ruleList.add(ruleMode);
            }
        }
        return ruleList.toArray(new String[0]);
    }

}
