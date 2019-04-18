package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Grade;
import com.qianfeng.vo.VGrade;
import com.qianfeng.vo.VStudent;

import java.util.List;

public interface GradeService {

    // 查询所有班级信息
    public List<Grade> findAll();

    // 分页查询所有班级展示信息
    public PageInfo findAllGradeByPage(Integer page, Integer limit);

    // 根据班级编号删除学生信息
    public void deleteGradeById(Integer id);

    // 根据班级编号查询班级展示详情
    public VGrade fingVGradeById(Integer id);

    // 选择性更新班级信息
    public int updateGrade(Grade grade);

    // 选择性添加新班级信息
    public int addGrade(Grade grade);


}
