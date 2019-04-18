package com.qianfeng.service;

import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Role;
import com.qianfeng.vo.VUser;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
public interface RoleService {


    public PageInfo findAllRole(int page,int limit);

    public void deleteById(int id);

    List<Role> queryRole(int id);

    boolean updateRole(int uid,int[] rids);

    boolean updatePower(int rid,int[] aids);
}
