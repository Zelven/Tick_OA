package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Staff;

import java.util.List;

public interface StaffService {

    PageInfo findAllStaff(int page, int count);

    void updateStaff(Staff staff);

    String generateIdService();

    String addStaff(Staff staff);

    /**
     * 返回所有员工信息的方法
     * @return
     */
    List<Staff> findAll();

    /**
     * 批量添加Staff的方法
     */
    void addStaffBatch(List<Staff> list);

    void deleteStaff(String no);

    Staff findStaffByNo(String no);

}
