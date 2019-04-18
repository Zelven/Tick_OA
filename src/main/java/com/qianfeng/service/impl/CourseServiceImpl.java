package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.CourseMapper;
import com.qianfeng.entity.Course;
import com.qianfeng.entity.Grade;
import com.qianfeng.service.CourseService;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseMapper courseDao;

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public PageInfo findAllCourseByPage(Integer page, Integer limit) {
        Integer index = PageUtil.getIndex(page, limit);

        List<Course> courseList = courseDao.findAllCourseBypage(index, limit);

        int count = courseDao.findCount();

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCount(count);
        pageInfo.setData(courseList);

        return pageInfo;
    }

    @Override
    public void deleteCourseById(Integer id) {
        Course course = courseDao.selectByPrimaryKey(id);
        if (null == course) {
            throw new RuntimeException("删除班级不存在");
        }

        int i = courseDao.deleteByPrimaryKey(id);
        System.out.println(i);
    }

    @Override
    public int addCourse(Course course) {

        Course cour = courseDao.findByName(course.getName());

        if (cour != null) {
            // 判断学科名称是否重复
            throw new RuntimeException("该学科名称已存在，请重新输入");
        }
        return courseDao.insertSelective(course);
    }

    @Override
    public Course findByName(String name) {
        return courseDao.findByName(name);
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateCourse(Course course) {
        Course cour = courseDao.findByName(course.getName());

        if (cour != null) {
            // 判断学科名称是否重复
            throw new RuntimeException("该学科名称已存在，请重新输入");
        }
        return courseDao.updateByPrimaryKeySelective(course);
    }


}
