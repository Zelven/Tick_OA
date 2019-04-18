package com.qianfeng.realm;

import com.qianfeng.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取合法登录的用户的用户名
        String name = (String)principals.getPrimaryPrincipal();
        // 根据用户名从数据库查询用户的权限和角色
        List<String> roles = userDao.findRoleByName(name);
        List<String> perms = userDao.findPermisionByName(name);
        // 转为set集合对象
        Set<String> roleSet = new HashSet<>(roles);
        Set<String> permSet = new HashSet<>(perms);
        // 创建授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(permSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取输入的用户名
        String name = (String)token.getPrincipal();
        // 根据用户名从数据库中查询密码
        String passowrd = userDao.findPwdByName(name);
        // 创建认证对象
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, passowrd, this.getName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, passowrd, ByteSource.Util.bytes("abc"), this.getName());

        return info;
    }
}
