package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.CheckMapper;
import com.qianfeng.entity.Check;
import com.qianfeng.entity.Process;
import com.qianfeng.service.CheckService;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/15/015
 */

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckMapper checkMapper;


    @Override
    public boolean insert(Check check) {
        checkMapper.insertSelective(check);

        return true;

    }

    @Override
    public boolean update(String tid, int id, int flag) {
        return false;
    }

    @Override
    public void delete(int id) {
       checkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo queryAll(int page,int limit) {
        int index = PageUtil.getIndex(page,limit);
        int count = checkMapper.queryCount();
        List<Process> list = checkMapper.selectAll();
        return PageUtil.createPage(count,list);

    }

    @Override
    public VEcharts queryBtEcharts(String pid) {
        List<EchartsItem> items = checkMapper.queryBtEcharts(pid);
        if (items != null){
            VEcharts vEcharts = new VEcharts();
            for (int i = 0; i < items.size(); i++) {
                EchartsItem item = items.get(i);
                if (i == 0){
                    vEcharts.setId(item.getId());
                }
                vEcharts.getDatax().add(item.getAname());
                vEcharts.getDatas1().add(item.getStime());
                vEcharts.getDatas2().add(item.getEtime());
                vEcharts.getDatas3().add(item.getDura());
            }
            return vEcharts;
        }
        return null;
    }

    @Override
    public VPage<VProcess> queryByName(String name) {

        VPage<VProcess> page = new VPage<>();
        page.setData(checkMapper.findByName(name));

        page.setCount(page.getData().size());
        page.setCode(0);
        return page;
    }


}
