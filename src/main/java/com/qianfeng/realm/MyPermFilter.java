package com.qianfeng.realm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.common.JsonBean;
import com.qianfeng.utils.JsonUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyPermFilter extends PermissionsAuthorizationFilter{

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        // 和ajax请求相关的请求头信息
        String header = httpRequest.getHeader("X-Requested-With");
        if(header != null && !header.equals("") && header.equals("XMLHttpRequest")){
            JsonBean bean = JsonUtils.createJsonBean(0, "没有访问权限");
            ObjectMapper objMapper = new ObjectMapper();
            // 通过响应返回json格式字符串
            response.getWriter().write(objMapper.writeValueAsString(bean));
        }else{
            // 非ajax方式，进行重定向
            httpResponse.sendRedirect(((HttpServletRequest) request).getContextPath() + "/unauthorized.jsp");
        }




        return false;
    }
}
