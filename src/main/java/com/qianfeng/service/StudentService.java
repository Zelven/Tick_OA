package com.qianfeng.service;

import com.qianfeng.entity.Student;
import com.qianfeng.vo.VStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    // 分页查询所有学生信息
    public List<VStudent> findAllStu(Integer index, Integer limit);

    // 查询所有学生信息总条数
    public int findCount();

    // 根据学生学号删除学生信息
    public void deleteStuByNo(String no);

    // 生成新学员学号
    public String createStuNo();

    // 添加学生信息
    public int addStudent(Student student);

    // 根据学生学号查询学生信息
    public Student findStuByNo(String no);

    // 选择性更新学生信息
    public int updateStu(Student student);

    // 批量添加学员信息
    public void addBatch(List<Student> list);

}
