package com.qianfeng.dao;

import com.qianfeng.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryAll(@Param("index") int index, @Param("limit")int limit);

    Integer queryCount();

    List<Role> queryRole(int id);

    List<Role> findAllRole();

    int insertRole(@Param("uid")int uid,@Param("rid")int rid);


}