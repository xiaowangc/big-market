package com.chige.test;

import com.alibaba.fastjson.JSON;
import com.chige.TestApplication;
import com.chige.trigger.api.IRaffleActivityService;
import com.chige.trigger.api.dto.ActivityDrawRequestDTO;
import com.chige.trigger.api.dto.ActivityDrawResponseDTO;
import com.chige.trigger.api.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wangyc
 * @Description TODO
 * @Date 2024/11/27 16:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ApiTest {

    @DubboReference(interfaceClass = IRaffleActivityService.class, version = "1.0")
    private IRaffleActivityService raffleActivityService;

    @Test
    public void test_rpc() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("xiaofuge");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}
