package com.qianfeng.dao;

import com.qianfeng.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    // 根据科目编号id删除科目信息
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    // 选择性添加新学科信息
    int insertSelective(Course record);

    // 根据学科编号id 查询学科信息
    Course selectByPrimaryKey(Integer id);

    // 选择性更新科目信息
    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    // 查询所有学科信息
    public List<Course> findAll();

    // 分页查询学科信息
    public List<Course> findAllCourseBypage(@Param("index") Integer index, @Param("limit") Integer limit);

    // 查询所有学科信息总条数
    public int findCount();

    // 根据学科名称查询学科信息
    public Course findByName(String name);



}