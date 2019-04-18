package com.qianfeng.dao;

import com.qianfeng.entity.Check;
import com.qianfeng.entity.Process;
import com.qianfeng.vo.EchartsItem;
import com.qianfeng.vo.VProcess;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/15/015
 */
public interface CheckMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    List<Process> selectAll();

    int queryCount();

    List<EchartsItem> queryBtEcharts(String pid);

    List<VProcess> findByName(String name);
}
