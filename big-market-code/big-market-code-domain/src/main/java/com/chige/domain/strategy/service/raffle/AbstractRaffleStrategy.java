package com.chige.domain.strategy.service.raffle;

import com.chige.domain.strategy.model.entity.RaffleAwardEntity;
import com.chige.domain.strategy.model.entity.RaffleFactorEntity;
import com.chige.domain.strategy.model.entity.RuleActionEntity;
import com.chige.domain.strategy.model.entity.StrategyEntity;
import com.chige.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.chige.domain.strategy.repository.IStrategyRepository;
import com.chige.domain.strategy.service.IRaffleStrategy;
import com.chige.domain.strategy.service.armory.IStrategyDispatch;
import com.chige.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.chige.types.enums.ResponseCode;
import com.chige.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @Author wangyc
 * @Description 抽奖策略抽象类，定义抽奖的标准流程
 * @Date 2024/11/30 14:43
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    protected IStrategyRepository strategyRepository;

    // 负责抽奖调度 -> 只负责抽奖处理，通过新增接口的方式，隔离职责，不需要关心调用抽奖的初始化
    protected IStrategyDispatch strategyDispatch;

    protected AbstractRaffleStrategy(IStrategyRepository strategyRepository, IStrategyDispatch strategyDispatch) {
        this.strategyRepository = strategyRepository;
        this.strategyDispatch = strategyDispatch;
    }

    /**
     * 指定抽奖流程 -> 模板化
     * @param raffleFactorEntity 抽奖因子
     * @return 抽奖结果
     */
    @Override
    public RaffleAwardEntity doRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (StringUtils.isBlank(userId) || Objects.isNull(strategyId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 策略查询
        StrategyEntity strategyEntity = strategyRepository.queryStrategyEntityByStrategyId(strategyId);

        // 抽奖前 - 校验过滤规则
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = doCheckRaffleBeforeLogic(RaffleFactorEntity.builder().userId(userId).strategyId(strategyId).build(), strategyEntity.ruleModels());

        if (Objects.isNull(ruleActionEntity)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        String code = ruleActionEntity.getCode();
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(code)) {
            RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 黑名单返回固定奖品id
                return RaffleAwardEntity.builder()
                        .awardId(raffleBeforeEntity.getAwardId())
                        .build();
            }else if (DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 权重根据返回的信息进行抽奖
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Integer awardId = strategyDispatch.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder()
                        .awardId(awardId)
                        .build();

            }
        }

        //默认抽奖流程
        Integer randomAwardId = strategyDispatch.getRandomAwardId(strategyId);
        return RaffleAwardEntity.builder()
                .awardId(randomAwardId)
                .build();

    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}