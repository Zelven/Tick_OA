package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Role;
import com.qianfeng.entity.User;
import com.qianfeng.vo.VMenu;
import com.qianfeng.vo.VPage;
import com.qianfeng.vo.VUser;

import java.util.List;

public interface UserService {
//    1645

    public User login(String name, String password);

    public void loginRememberMe(String no, String password, boolean rememberMe);

    PageInfo findAllUser(int page, int limit,String no);

    void deleteById(int id);

    User selectByName(String name);

    List<VMenu> queryMenuById(int id);


//    List<VUser> findByCondition(String no,int flag);

    boolean updateFlag(int flag,int id);

    boolean insert(User user);

    boolean insertBatch(List<User> list);

    List<User> findLeader();

    void addUser(User user);
}
