package com.qianfeng.dao;

import com.qianfeng.entity.UserRoleKey;
import org.apache.ibatis.annotations.Insert;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    @Insert("insert into t_userrole(uid, rid) values(#{uid}, #{rid})")
    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);
}