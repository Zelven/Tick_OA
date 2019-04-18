package com.qianfeng.service;

import com.qianfeng.entity.LoginLog;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
public interface LoginLogService {

    boolean insert(String no);
    List<LoginLog> queryAll();

    List<LoginLog> queryByNo(String no);

    boolean insert(LoginLog record);
}
