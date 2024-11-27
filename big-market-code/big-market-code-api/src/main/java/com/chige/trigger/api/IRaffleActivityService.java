package com.chige.trigger.api;

import com.chige.api.response.Response;
import com.chige.trigger.api.dto.ActivityDrawRequestDTO;
import com.chige.trigger.api.dto.ActivityDrawResponseDTO;

/**
 * @Author wangyc
 * @Description 抽奖活动服务
 * @Date 2024/11/27 16:16
 */
public interface IRaffleActivityService {


    /**
     * 活动抽奖接口
     *
     * @param request 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

}
