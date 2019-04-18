package com.qianfeng.dao;

import com.qianfeng.entity.LoginLog;

import java.util.List;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> queryAll();

    List<LoginLog> queryByNo(String no);
}