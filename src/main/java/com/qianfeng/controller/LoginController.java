package com.qianfeng.controller;

import com.qianfeng.common.CommonInfo;
import com.qianfeng.common.JsonBean;
import com.qianfeng.entity.LoginLog;
import com.qianfeng.entity.User;
import com.qianfeng.service.LoginLogService;
import com.qianfeng.service.UserService;
import com.qianfeng.utils.IpInfo;
import com.qianfeng.utils.IpUtils;
import com.qianfeng.utils.JsonIp;
import com.qianfeng.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//1645
//fetch
//123
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserService userService;
    @RequestMapping("/login.do")
    @ResponseBody
    public JsonBean login(String name, String password,boolean rememberMe, HttpSession session){

        try {
            User user = userService.login(name,password);

            session.setAttribute(CommonInfo.LOGIN_USER,user);

            LoginLog log = new LoginLog();
            log.setIp("117.159.15.221");
            log.setCreatetime(new Date());
            log.setNo(user.getNo());
            log.setLocation("河南省郑州市");
            loginLogService.insert(log);
            return JsonUtils.createJsonBean(1, null);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.createJsonBean(0,e.getMessage());
        }

    }

    public static final String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;
    }
}
