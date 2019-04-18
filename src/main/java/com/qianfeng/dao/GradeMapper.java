package com.qianfeng.dao;

import com.qianfeng.entity.Grade;
import com.qianfeng.vo.VGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    // 选择性添加新班级信息
    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    // 选择性更新班级信息
    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    // 查询所有班级信息
    public List<Grade> findAll();

    // 分页查询班级信息
    public List<VGrade> findAllGradeBypage(@Param("index") Integer index, @Param("limit") Integer limit);

    // 查询所有班级信息总条数
    public int findCount();

    // 根据班级编号查询班级展示详情
    public VGrade fingVGradeById(Integer id);

    // 根据班级名称或位置查询班级信息
    public Grade findByNameOrLocation(@Param(value = "name") String name,
                                      @Param(value = "location") String location,
                                      @Param(value = "flag") Integer flag);

    
}