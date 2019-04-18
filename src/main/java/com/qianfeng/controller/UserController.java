package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Role;
import com.qianfeng.entity.User;
import com.qianfeng.service.RoleService;
import com.qianfeng.service.UserService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VResult;
import com.qianfeng.vo.VUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/15/015
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/userall.do")
    public LayUIListJsonBean findAllUser(int page, int limit, String no) {
        PageInfo pageInfo = userService.findAllUser(page, limit, no);

        return JsonUtils.createListBean(0, pageInfo);
    }

    @RequestMapping("/userdel.do")
    public JsonBean deleleById(int id) {
        userService.deleteById(id);
        return JsonUtils.createJsonBean(1000, null);
    }

    /*@RequestMapping("/find.do")
    public LayUIListJsonBean findByCondition(int page,int limit,String no,int flag){

        PageInfo pageInfo = userService.findByCondition(page,limit,no,flag);
        return JsonUtils.createListBean(0,pageInfo);
    }*/


    @RequestMapping("/userroleedit.do")
    public VResult usereidt(int id, int[] rids) {
        if (roleService.updateRole(id, rids)) {
            return VResult.setOk("ok");

        } else {
            return VResult.setERROR("Error");
        }
    }

    @RequestMapping("/leader.do")
    @ResponseBody
    public List<User> findLeader() {
        return userService.findLeader();
    }

    @RequestMapping("useradd.do")
    @ResponseBody
    public JsonBean userAdd(User user) {
        String passwordUnencityped = user.getPassword();

        // 控制器加密password
        Md5Hash md5Hash = new Md5Hash(passwordUnencityped,"abc",1);
        String password = String.valueOf(md5Hash);
        user.setPassword(password);


        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("已存在该用户，无法添加");
        }

        return JsonUtils.createJsonBean(1,null);
    }


}
