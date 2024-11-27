package com.chige.test.infrastructure;

import com.alibaba.fastjson.JSON;
import com.chige.infrastructure.persistent.dao.IAwardDao;
import com.chige.infrastructure.persistent.po.Award;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author wangyc
 * @Description TODO
 * @Date 2024/11/27 22:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardDaoTest {

    @Resource
    private IAwardDao awardDao;

    @Test
    public void testSelectList() {
        List<Award> awards = awardDao.selectList();
        log.info("查询结果：{}", JSON.toJSONString(awards));
    }
}
