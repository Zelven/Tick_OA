package com.qianfeng.dao;

import com.qianfeng.entity.Student;
import com.qianfeng.vo.VStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    // 根据学生学号删除学生信息
    int deleteByPrimaryKey(String no);

    int insert(Student record);

    // 选择性添加学生
    int insertSelective(Student record);

    // 根据学生学号查询学生信息
    Student selectByPrimaryKey(String no);

    // 选择性更新学生信息
    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    // 分页查询所有学生信息
    List<VStudent> findAllStu(@Param("index") Integer index, @Param("limit") Integer limit);

    // 查询所有学生信息总条数
    public int findCount();

    // 查询表中最后一名学生信息
    public Student findLastStu();

    // 批量添加学员信息
    public void addBatch(List<Student> list);

}