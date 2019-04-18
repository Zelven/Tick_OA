package com.qianfeng.controller;


import com.qianfeng.common.CommonInfo;
import com.qianfeng.common.JsonBean;
import com.qianfeng.entity.User;
import com.qianfeng.entity.UserRoleKey;
import com.qianfeng.service.AuthorityService;
import com.qianfeng.service.UserService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.vo.VMenu;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */

@RestController
public class MenuController {


    @Autowired
    private UserService userService;

    @RequestMapping("/usermenu.do")
    public List<VMenu> findMenu(HttpSession session){
        User user = (User) session.getAttribute(CommonInfo.LOGIN_USER);
        List<VMenu> parent = userService.queryMenuById(user.getId());
        return parent;
    }
}
