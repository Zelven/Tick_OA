package com.qianfeng.dao;

import com.qianfeng.entity.Authority;
import com.qianfeng.entity.Role;
import com.qianfeng.entity.User;
import com.qianfeng.vo.VMenu;
import com.qianfeng.vo.VUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

//1645
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 作者：zhFinal
     */
    String findPwdByName(String name);

    List<String> findRoleByName(String name);

    List<String> findPermisionByName(String name);

    List<VUser> findAllUser(@Param("index") int index, @Param("limit")int limit,@Param("no")String no);

    public User selectByNo(String no);

    Integer queryCount();

    Integer queryCountByCondition(String no,int flag);

    void deleteById(int id);

    /**
     * 查询用户权限的方法
     * @param id
     * @return
     */
    List<VMenu> queryMenu(int id);

    List<VUser> findByCondition(@Param("no") String no,@Param("flag")int flag);

    List<User> findLeader();

    int updateFlag(@Param("flag")Integer flag, @Param("id")int id);

    void addUser(User user);
}
