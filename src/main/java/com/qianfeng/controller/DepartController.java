package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Department;
import com.qianfeng.service.DepartService;
import com.qianfeng.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DepartController {

    @Autowired
    private DepartService departService;

    @RequestMapping("departpage.do")
    @ResponseBody
    public LayUIListJsonBean findPage(int page, int limit){

        PageInfo pageInfo =  departService.findAllDept(page,limit);

        return JsonUtils.createListBean(0,pageInfo);
    }


    @RequestMapping("departadd.do")
    public String addDept(String name, String createtime){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = format.parse(createtime);
            departService.addDept(name,date);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "departlist";
    }


    @RequestMapping("departdelete.do")
    @ResponseBody
    public JsonBean deleteDept(Integer id){
        departService.deleteDept(id);
        return JsonUtils.createJsonBean(1000,null);
    }

    @RequestMapping("departupdate.do")
    public String updateDept(String name, Integer id){
        try {
            departService.updateDept(name,id);
            return "departlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("departall.do")
    @ResponseBody
    public List<Department> queryAllDept(){
        List<Department> list = departService.queryAll();
        return list;
    }


}
