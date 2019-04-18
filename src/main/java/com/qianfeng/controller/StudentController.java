package com.qianfeng.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.entity.Student;
import com.qianfeng.service.StudentService;
import com.qianfeng.utils.ImportExcel;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.utils.LayuiJsonUtils;
import com.qianfeng.utils.PageUtil;
import com.qianfeng.vo.VStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/studentpage.do")
    public LayUIListJsonBean findAllStuByPage(Integer page, Integer limit) {

        int index = PageUtil.getIndex(page, limit);

        int count = studentService.findCount();

        List<VStudent> vStudentList = studentService.findAllStu(index, limit);

        return LayuiJsonUtils.createListBean(0, vStudentList, count);

    }


    @RequestMapping("/studentdelete.do")
    public JsonBean deleteStuById(String no) {

        studentService.deleteStuByNo(no);

        return JsonUtils.createJsonBean(1000, null);

    }

    @RequestMapping("/createno.do")
    public JsonBean createStuNo() {

        String no = studentService.createStuNo();
        Map<String, Object> map = new HashMap<>();
        map.put("no", no);
        return JsonUtils.createJsonBean(1, map);

    }

    @RequestMapping("/studentadd.do")
    public JsonBean addStudent(Student student) {

        int i = studentService.addStudent(student);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }

    @RequestMapping("/studentquery.do")
    public JsonBean queryByNo(String no) {

        Student stu = studentService.findStuByNo(no);

        return JsonUtils.createJsonBean(1, stu);

    }

    @RequestMapping("/studentupdate.do")
    public JsonBean updateStudent(Student student) {

        int i = studentService.updateStu(student);
        System.out.println(i);

        return JsonUtils.createJsonBean(1, null);

    }

    @RequestMapping("/studentbatch.do")
    public JsonBean importStudentExcel(@RequestParam MultipartFile excelFile) {

        try {
            // 获取上传文件的输入流
            InputStream inputStream = excelFile.getInputStream();

            // 获取文件名
            String fileName = excelFile.getOriginalFilename();

            // 使用工具类中的方法，获取上传excel文件中的List<Map<String,Object>>形式的数据
            List<Map<String, Object>> sourceList = ImportExcel.readExcel(fileName, inputStream);

            // ObjectMapper类是Jackson库的主要类。它提供一些功能,将【Java对象】转换成匹配【JSON结构】，反之亦然,
            // 它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写。
            ObjectMapper ObjMapper = new ObjectMapper();
            // 将【sourceList对象】先转为【json格式字符串：infos】，再转为 List<SysUser>对象
            String infos = ObjMapper.writeValueAsString(sourceList);

            // 将【json格式字符串：infos】 转为【List<SysUser>对象：list】
            // readValue(String,Object)方法：参一：待转字符串；参二：目标类对象
            // 使用new TypeReference（匿名内部类）：可以明确的指定反序列化的类型（泛型）,获取父类真实实现类并创建对应类对象，即获取到目标类对象，完成转换
            List<Student> list = ObjMapper.readValue(infos, new TypeReference<List<Student>>() {});

            // 将准备好的list数据，调用批量添加方法，实现添加
            studentService.addBatch(list);

            return JsonUtils.createJsonBean(1, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return JsonUtils.createJsonBean(0, e.getMessage());
        }
    }


}
