package com.qianfeng.test;

import com.qianfeng.dao.UserDao;
import com.qianfeng.vo.VMenu;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-bean.xml")
@Transactional
public class BaseTest {

    @Autowired
    private UserDao ud;

    @Test
    public void findMenu(){
        List<VMenu> list = new ArrayList<>();
        list = ud.queryMenu(2);
        System.out.println("权限");
        for (VMenu vMenu : list) {
            System.out.println("权限");
            System.out.println(vMenu);
        }

    }


    @Test
    public void Demo1() {
        System.out.println("Hello world!");
    }

    @Test
    public void HashTest(){
        // e99a18c428cb38d5f260853678922e03  ----- 123
        // 7d61f71f34b0305aabc5d1cdd9d2a777 ------ admin
        Md5Hash md5Hash = new Md5Hash("123" , "abc" ,1);
        String md5 = String.valueOf(md5Hash);
        System.out.println("密码是：\n" + md5);
    }

}
