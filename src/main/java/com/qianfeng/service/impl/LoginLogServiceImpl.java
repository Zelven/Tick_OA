package com.qianfeng.service.impl;

import com.qianfeng.dao.LoginLogMapper;
import com.qianfeng.entity.LoginLog;
import com.qianfeng.service.LoginLogService;


import org.apache.log4j.helpers.LogLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper logMapper;
    @Override
    public boolean insert(String no) {
        return false;
    }

    @Override
    public List<LoginLog> queryAll() {
        return logMapper.queryAll();
    }

    @Override
    public List<LoginLog> queryByNo(String no) {
        return logMapper.queryByNo(no);
    }

    @Override
    public boolean insert(LoginLog record) {
        return logMapper.insert(record) > 0;
    }
}
