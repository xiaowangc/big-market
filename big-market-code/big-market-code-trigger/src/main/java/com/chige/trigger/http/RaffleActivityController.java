package com.chige.trigger.http;

import com.chige.trigger.api.IRaffleActivityService;
import com.chige.trigger.api.dto.ActivityDrawRequestDTO;
import com.chige.trigger.api.dto.ActivityDrawResponseDTO;
import com.chige.trigger.api.response.Response;
import com.chige.types.enums.ResponseCode;
import com.chige.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaownagc
 * @description 抽奖活动服务 注意；在不引用 application/case 层的时候，就需要让接口实现层来做领域的串联。一些较大规模的系统，需要加入 case 层。
 * @create 2024-04-13 09:42
 */
@Slf4j
@RestController()
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/activity/")
@DubboService(version = "1.0")
public class RaffleActivityController implements IRaffleActivityService {

    private final SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyyMMdd");


    @RequestMapping(value = "draw", method = RequestMethod.POST)
    @Override
    public Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request) {
        return Response.<ActivityDrawResponseDTO>builder().build();
    }
}
