package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.StaffDao;
import com.qianfeng.entity.Department;
import com.qianfeng.entity.Staff;
import com.qianfeng.service.StaffService;
import com.qianfeng.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {


    @Autowired
    private StaffDao staffDao;

    @Override
    public PageInfo findAllStaff(int page, int limit) {
        int index = PageUtil.getIndex(page,limit);
        int count = staffDao.staffCnt();
        List<Staff> staffList = staffDao.findAllStaff(index,limit);
        return PageUtil.createPage(count,staffList);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffDao.updateByPrimaryKeySelective(staff);
    }

    @Override
    public String generateIdService() {
        byte[] idNum = new byte[6];
        while (true) {
            String id = "qf";
            for (byte b : idNum) {
                b = (byte) (Math.random() * 10);
                id += b;
            }

            if( staffDao.selectByPrimaryKey(id) == null){
                return id;
            }
        }
    }

    @Override
    public String addStaff(Staff staff) {
        if(staffDao.selectByPrimaryKey(staff.getNo()) != null){
            String no = generateIdService();
            staff.setNo(no);
        }
        staffDao.insertSelective(staff);
        return staff.getNo();
    }

    @Override
    public List<Staff> findAll() {
        return staffDao.findAll();
    }

    @Override
    public void addStaffBatch(List<Staff> list) {
        int count = 1;
        List<Staff> tempList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            tempList.add(list.get(i));
            if(count % 100 != 0){
                count++;
            }else{
                staffDao.addBatch(tempList);
                tempList.clear();
                count = 1;
            }
        }
        if(tempList.size() != 0){
            staffDao.addBatch(tempList);
        }
    }

    @Override
    public void deleteStaff(String no) {
        staffDao.deleteByPrimaryKey(no);
    }

    @Override
    public Staff findStaffByNo(String no) {
        return staffDao.selectByPrimaryKey(no);
    }
}
