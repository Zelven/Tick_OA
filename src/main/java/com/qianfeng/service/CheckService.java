package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Check;
import com.qianfeng.entity.Process;
import com.qianfeng.vo.VCheck;
import com.qianfeng.vo.VEcharts;
import com.qianfeng.vo.VPage;
import com.qianfeng.vo.VProcess;

/**
 * @author Zelven
 * @date 2019/4/15/015
 */


public interface CheckService {

    public boolean insert(Check check);

    public boolean update(String tid,int id,int flag);

    public void delete(int id);

    public PageInfo queryAll(int page,int limit);

    public VEcharts queryBtEcharts(String pid);

    public VPage<VProcess> queryByName(String name);






}
