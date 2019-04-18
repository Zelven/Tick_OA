package com.qianfeng.dao;

import com.qianfeng.entity.Ddress;

public interface DdressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ddress record);

    int insertSelective(Ddress record);

    Ddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ddress record);

    int updateByPrimaryKey(Ddress record);
}