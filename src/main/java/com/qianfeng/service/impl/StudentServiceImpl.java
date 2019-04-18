package com.qianfeng.service.impl;

import com.qianfeng.dao.StudentMapper;
import com.qianfeng.entity.Student;
import com.qianfeng.service.StudentService;
import com.qianfeng.vo.VStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentDao;

    @Override
    public List<VStudent> findAllStu(Integer index, Integer limit) {

        List<VStudent> vStudentList = studentDao.findAllStu(index, limit);

        return vStudentList;
    }

    @Override
    public int findCount() {

        return studentDao.findCount();

    }


    @Override
    public void deleteStuByNo(String no) {

        Student student = studentDao.selectByPrimaryKey(no);
        if (null == student) {
            throw new RuntimeException("删除学生不存在");
        }

        studentDao.deleteByPrimaryKey(no);

    }

    @Override
    public String createStuNo() {

        Student lastStu = studentDao.findLastStu();

        String lastStuNo = lastStu.getNo();

        while (true) {
            try {
                Integer i = Integer.parseInt(lastStuNo.substring(7));
                String no = "qf00000" + Integer.toString(i + 1);
                if (studentDao.selectByPrimaryKey(no) == null) {
                    return no;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int addStudent(Student student) {

        Student stu = studentDao.selectByPrimaryKey(student.getNo());
        if (stu != null) {
            // 判断学生学号是否重复
            throw new RuntimeException("该学生学号已存在，请重新输入");
        } else if (stu != null && stu.getCardno() == student.getCardno()) {
            // 判断学生身份证号码是否重复
            throw new RuntimeException("该学生身份证号码已存在，请重新输入");
        }

        return studentDao.insertSelective(student);
    }

    @Override
    public Student findStuByNo(String no) {
        return studentDao.selectByPrimaryKey(no);
    }

    @Override
    public int updateStu(Student student) {

        Student stu = studentDao.selectByPrimaryKey(student.getNo());
        if (stu != null) {
            // 判断学生学号是否重复
            throw new RuntimeException("该学生学号已存在，请重新输入");
        } else if (stu != null && stu.getCardno() == student.getCardno()) {
            // 判断学生身份证号码是否重复
            throw new RuntimeException("该学生身份证号码已存在，请重新输入");
        }
        return studentDao.updateByPrimaryKeySelective(student);
    }

    @Override
    public void addBatch(List<Student> list) {

        // 这里需要对控制层传来的list数据做拆分，
        // 一：提高添加效率； 二：sql语句也不可以无限长添加数据；
        // 这里拆分为每【100】条数据添加一次
        int count = 1;

        List<Student> tempList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            tempList.add(list.get(i));

            if (count % 100 != 0) {
                count++;
            } else {
                // 完成添加
                studentDao.addBatch(tempList);
                tempList.clear();
                count = 1;
            }

        }

        // 将剩余不为整百条的数据实现添加
        if (tempList.size() != 0) {
            studentDao.addBatch(tempList);
        }

    }
}
