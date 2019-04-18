package com.qianfeng.dao;

import com.qianfeng.entity.Authority;
import com.qianfeng.entity.Role;
import com.qianfeng.vo.VMenu;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);



    //新增
    int save(Authority authority);




    List<Authority> queryAll();


    //查询数量
    Integer queryCount(String where);

}