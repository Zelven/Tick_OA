package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Course;
import com.qianfeng.entity.Grade;

import java.util.List;

public interface CourseService {

    // 查询所有班级信息
    public List<Course> findAll();

    // 分页查询所有班级展示信息
    public PageInfo findAllCourseByPage(Integer page, Integer limit);

    // 根据班级编号删除科目信息
    public void deleteCourseById(Integer id);

    // 根据学科名称查询学科信息
    public Course findByName(String name);

    // 根据学科名称查询学科信息
    public Course findById(Integer id);

    // 选择性更新学科信息
    public int updateCourse(Course course);

    // 选择性添加新学科信息
    public int addCourse(Course course);


}
