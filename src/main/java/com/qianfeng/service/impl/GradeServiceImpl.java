package com.qianfeng.service.impl;

import com.qianfeng.common.PageInfo;
import com.qianfeng.dao.GradeMapper;
import com.qianfeng.entity.Grade;
import com.qianfeng.entity.Student;
import com.qianfeng.service.GradeService;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeDao;

    @Override
    public List<Grade> findAll() {
        return gradeDao.findAll();
    }

    @Override
    public PageInfo findAllGradeByPage(Integer page, Integer limit) {

        Integer index = PageUtil.getIndex(page, limit);

        List<VGrade> vGradeList = gradeDao.findAllGradeBypage(index, limit);

        int count = gradeDao.findCount();

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCount(count);
        pageInfo.setData(vGradeList);

        return pageInfo;
    }

    @Override
    public void deleteGradeById(Integer id) {
        Grade grade = gradeDao.selectByPrimaryKey(id);
        if (null == grade) {
            throw new RuntimeException("删除班级不存在");
        }

        int i = gradeDao.deleteByPrimaryKey(id);
        System.out.println(i);
    }

    @Override
    public VGrade fingVGradeById(Integer id) {
        return gradeDao.fingVGradeById(id);
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeDao.updateByPrimaryKeySelective(grade);
    }

    @Override
    public int addGrade(Grade grade) {

        Grade gra1 = gradeDao.findByNameOrLocation(grade.getName(), null,  grade.getFlag());
        Grade gra2 = gradeDao.findByNameOrLocation(null, grade.getLocation(), grade.getFlag());

        if (gra1 != null) {
            // 判断班级名称是否重复
            throw new RuntimeException("该班级名称已存在，请重新输入");
        } else if (gra2 != null) {
            // 判断班级地址是否重复
            throw new RuntimeException("该班级地址已被占用，请重新输入");
        }
        return gradeDao.insertSelective(grade);
    }


}
