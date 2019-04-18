package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.DepartmentDao;
import com.qianfeng.entity.Department;
import com.qianfeng.service.DepartService;
import com.qianfeng.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartmentDao departmentDao;


    @Override
    public PageInfo findAllDept(int page, int limit) {
        int index = PageUtil.getIndex(page,limit);
        int count = departmentDao.findDeptCnt();
        List<Department> deptList = departmentDao.findAllDept(index,limit);
        return PageUtil.createPage(count,deptList);

    }

    @Override
    public void addDept(String name, Date createtime) {
        Department department = new Department();
        department.setName(name);
        department.setCreatetime(createtime);
        department.setFlag(1);
        departmentDao.insertSelective(department);
    }

    @Override
    public void deleteDept(Integer id) {
        departmentDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateDept(String name, Integer id) {
        Department dept = new Department();
        dept.setId(id);
        dept.setName(name);
        departmentDao.updateByPrimaryKeySelective(dept);
    }

    @Override
    public List<Department> queryAll() {
        return departmentDao.queryAll();
    }
}
