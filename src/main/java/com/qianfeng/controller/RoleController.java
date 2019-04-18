package com.qianfeng.controller;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Role;
import com.qianfeng.service.RoleService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.vo.VUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleall.do")
    public LayUIListJsonBean findAll(Integer page , Integer limit){
        PageInfo info = roleService.findAllRole(page, limit);
        return JsonUtils.createListBean(0,info);
    }

    @RequestMapping("/delrole.do")
    public JsonBean deleteRoleById(int id){
        roleService.deleteById(id);
        return JsonUtils.createJsonBean(1000,null);
    }

    //更改权限updatePower（）

}
