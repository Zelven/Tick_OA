package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.StaffDao;
import com.qianfeng.dao.UserDao;
import com.qianfeng.dao.UserRoleMapper;
import com.qianfeng.entity.Staff;
import com.qianfeng.entity.User;
import com.qianfeng.entity.UserRoleKey;
import com.qianfeng.service.UserService;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VMenu;
import com.qianfeng.vo.VUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleMapper userRoleDao;

    @Override
    public User login(String name, String password) {
        // shiro中提供的认证操作方法
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        // 登出
        //subject.logout();
        User user = userDao.selectByNo(name);
        return user;
    }

    @Override
    public void loginRememberMe(String name, String password,boolean rememberMe) {
        // shiro中提供的认证操作方法
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
//        if(rememberMe){
            // 何止是否记住我
            token.setRememberMe(rememberMe);
//        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @Override
    public PageInfo findAllUser(int page, int limit,String no) {
        int index = PageUtil.getIndex(page,limit);
        int count = userDao.queryCount();
        List<VUser> list = userDao.findAllUser(index,limit,no);
        return PageUtil.createPage(count,list);

    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User selectByName(String name) {
        return userDao.selectByNo(name);
    }


    @Override
    public List<VMenu> queryMenuById(int id) {
        return userDao.queryMenu(id);
    }



    @Override
    public boolean updateFlag(int flag, int id) {
        return userDao.updateFlag(flag,id)>0;
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean insertBatch(List<User> list) {
        try {
            for (User user : list) {
                userDao.insert(user);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /*@Override
    public PageInfo findByCondition(int page, int limit, String no, int flag) {
        int index = PageUtil.getIndex(page,limit);
        int count = userDao.queryCountByCondition(no,flag);
        List<VUser> list = userDao.findByCondition(no,flag);
        return PageUtil.createPage(count,list);
    }*/

    @Override
    public List<User> findLeader() {
        return userDao.findLeader();
    }

    @Override
    public void addUser(User user) {

        int uid = userDao.insertSelective(user);
        UserRoleKey key = new UserRoleKey();
        key.setUid(uid);
        key.setRid(4);
        userRoleDao.insert(key);

    }



}
