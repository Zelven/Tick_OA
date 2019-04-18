package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Grade;
import com.qianfeng.service.GradeService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.utils.LayuiJsonUtils;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VGrade;
import com.qianfeng.vo.VStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/gradeall.do")
    @ResponseBody
    public List findAll() {

        List<Grade> list = gradeService.findAll();

        return list;

    }

    @RequestMapping("/gradepage.do")
    @ResponseBody
    public LayUIListJsonBean findAllStuByPage(Integer page, Integer limit) {

        PageInfo pageInfo = gradeService.findAllGradeByPage(page, limit);

        Integer count = pageInfo.getCount();
        List<VGrade> vGradeList = (List<VGrade>) pageInfo.getData();

        return LayuiJsonUtils.createListBean(0, vGradeList, count);

    }


    @RequestMapping("/gradedelete.do")
    @ResponseBody
    public JsonBean deleteStuById(Integer id) {

        gradeService.deleteGradeById(id);

        return JsonUtils.createJsonBean(1000, null);

    }

    @RequestMapping("/gradequery.do")
    @ResponseBody
    public JsonBean queryVGradeById(Integer id) {

        VGrade vGrade = gradeService.fingVGradeById(id);

        return JsonUtils.createJsonBean(1, vGrade);

    }


    @RequestMapping("/gradeupdate.do")
    @ResponseBody
    public JsonBean updateGrade(Integer id, String name, Integer week, String location) {

        Grade grade = new Grade();
        grade.setId(id);
        grade.setName(name);
        grade.setWeek(week);
        grade.setLocation(location);

        int i = gradeService.updateGrade(grade);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }


    @RequestMapping("/gradeadd.do")
    @ResponseBody
    public JsonBean addGrade(Grade grade) {

        int i = gradeService.addGrade(grade);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }



}
