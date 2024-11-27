package com.chige.infrastructure.persistent.dao;

import com.chige.infrastructure.persistent.po.Award;

import java.util.List;

/**
 * @Author wangyc
 * @Description 奖品持久层
 * @Date 2024/11/27 22:16
 */
public interface IAwardDao {

    Integer insert(Award award);

    List<Award> selectList();

}