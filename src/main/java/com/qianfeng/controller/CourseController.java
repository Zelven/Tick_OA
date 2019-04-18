package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Course;
import com.qianfeng.entity.Grade;
import com.qianfeng.service.CourseService;
import com.qianfeng.service.GradeService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.utils.LayuiJsonUtils;
import com.qianfeng.vo.VGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courseall.do")
    @ResponseBody
    public List findAll() {

        List<Course> list = courseService.findAll();

        return list;

    }

    @RequestMapping("/coursepage.do")
    @ResponseBody
    public LayUIListJsonBean findAllCourseByPage(Integer page, Integer limit) {

        PageInfo pageInfo = courseService.findAllCourseByPage(page, limit);

        Integer count = pageInfo.getCount();
        List<Course> courseList = (List<Course>) pageInfo.getData();

        return LayuiJsonUtils.createListBean(0, courseList, count);

    }


    @RequestMapping("/coursedelete.do")
    @ResponseBody
    public JsonBean deleteCourseById(Integer id) {

        courseService.deleteCourseById(id);

        return JsonUtils.createJsonBean(1000, null);

    }

    @RequestMapping("/courseadd.do")
    @ResponseBody
    public JsonBean addCourse(Course course) {

        int i = courseService.addCourse(course);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }

    @RequestMapping("/coursequery.do")
    @ResponseBody
    public JsonBean queryCourseById(Integer id) {

        Course course = courseService.findById(id);

        return JsonUtils.createJsonBean(1, course);

    }


    @RequestMapping("/courseupdate.do")
    @ResponseBody
    public JsonBean updateCourse(Course course) {

        int i = courseService.updateCourse(course);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }


}
