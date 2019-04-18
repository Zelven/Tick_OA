package com.qianfeng.dao;

import com.qianfeng.entity.Department;
import com.qianfeng.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//1645
public interface DepartmentDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> findAllDept(@Param("index") int index, @Param("limit") int limit);

    Integer findDeptCnt();

    @Select("select id,name from t_depart where flag=1")
    @ResultType(Department.class)
    List<Department> queryAll();
}