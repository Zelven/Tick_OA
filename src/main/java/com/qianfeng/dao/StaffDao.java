package com.qianfeng.dao;

import com.qianfeng.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffDao {

    int deleteByPrimaryKey(String no);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> findAllStaff(@Param("index") int index, @Param("limit") int limit);

    Integer staffCnt();

    void addBatch(List<Staff> staffList);

    /**
     * 查询所有员工信息
     */
    List<Staff> findAll();

}