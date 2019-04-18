package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.RoleMapper;
import com.qianfeng.entity.Role;
import com.qianfeng.service.RoleService;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
   public PageInfo findAllRole(int page, int limit){
        int index = PageUtil.getIndex(page, limit);
        List<Role> list = roleMapper.queryAll(index,limit);
        PageInfo info = PageUtil.createPage(roleMapper.queryCount(), list);
        return info;
    }
    @Override
    public void deleteById(int id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> queryRole(int id) {
        return roleMapper.queryRole(id);
    }

    @Override
    public boolean updateRole(int uid, int[] rids) {
        try {
            roleMapper.deleteByPrimaryKey(uid);
            for (int rid : rids) {
                roleMapper.insertRole(uid,rid);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePower(int rid, int[] aids) {
        try {
            roleMapper.deleteByPrimaryKey(rid);
            for (int i = 0; i < aids.length; i++) {
                roleMapper.insertRole(rid,aids[i]);

            }
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
