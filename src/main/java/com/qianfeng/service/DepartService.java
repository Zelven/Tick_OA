package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Department;

import java.util.Date;
import java.util.List;

public interface DepartService {

    PageInfo findAllDept(int page, int count);

    void addDept(String name, Date createtime);

    void deleteDept(Integer id);

    void updateDept(String name, Integer id);

    List<Department> queryAll();
}
