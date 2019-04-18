package com.qianfeng.service.impl;

import com.qianfeng.dao.AuthorityMapper;
import com.qianfeng.entity.Authority;
import com.qianfeng.entity.Role;
import com.qianfeng.service.AuthorityService;
import com.qianfeng.vo.VMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zelven
 * @date 2019/4/13/013
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;


    @Override
    public int save(Authority authority) {
        return authorityMapper.save(authority);
    }

    @Override
    public List<Authority> queryAll() {
        return authorityMapper.queryAll();
    }


}
