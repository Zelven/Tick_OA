package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Check;
import com.qianfeng.entity.User;
import com.qianfeng.service.CheckService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.vo.VEcharts;
import com.qianfeng.vo.VPage;
import com.qianfeng.vo.VProcess;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Zelven
 * @date 2019/4/16/016
 */
@Controller
public class CheckContoller {
    @Autowired
    private CheckService checkService;


    @RequestMapping("/processlist.do")
    @ResponseBody
    public LayUIListJsonBean selectAll(int page,int limit){
        PageInfo pageInfo = checkService.queryAll(page,limit);
        return JsonUtils.createListBean(0,pageInfo);

    }

    @RequestMapping("/processecharts.do")
    @ResponseBody
    public VEcharts processTb(String pid){
        return checkService.queryBtEcharts(pid);
    }


    @RequestMapping("/processdel.do")
    @ResponseBody
    public JsonBean deleteById(int id){
        checkService.delete(id);
        return JsonUtils.createJsonBean(1000,null);
    }

    @RequestMapping("/processnolist.do")
    @ResponseBody
    public VPage<VProcess> processNoList(){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
        return checkService.queryByName(user.getName());
    }

    @RequestMapping("/processadd.do")
    public String processadd(Check check,String rname, HttpSession session){
        User user = (User) session.getAttribute("user");
        String name = user.getName();
        String no = user.getNo();
        check.setStartno(no);
        check.setStartname(name);
        check.setFlag(1);
        check.setPid((int) (Math.random() * 10000));
        checkService.insert(check);
        return "redirect:/processlist.html";
    }

}
