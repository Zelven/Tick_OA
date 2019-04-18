package com.qianfeng.service;

import com.qianfeng.entity.Authority;
import com.qianfeng.entity.Role;
import com.qianfeng.vo.VMenu;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
public interface AuthorityService {


    //新增
    int save(Authority authority);

    //查询
    List<Authority> queryAll();

}
