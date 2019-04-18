package com.qianfeng.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;
import com.qianfeng.entity.Staff;
import com.qianfeng.service.StaffService;
import com.qianfeng.utils.ExcelUtils;
import com.qianfeng.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping("staffpage.do")
    @ResponseBody
    public LayUIListJsonBean findPage(int page, int limit) {

        PageInfo pageInfo = staffService.findAllStaff(page, limit);

        return JsonUtils.createListBean(0, pageInfo);
    }

    @RequestMapping("photoupload.do")
    @ResponseBody
    public LayUIListJsonBean upload(@RequestParam("file") MultipartFile upFile, HttpServletRequest request) {

        // 获取上传文件的文件名
        String fileName = upFile.getOriginalFilename();

        String path = request.getServletContext().getRealPath("/");
        System.out.println(path);
        File parentPath = new File(path);
        // 获取父级目录的路径
        path = parentPath.getParent() + "/webapp/media/images";

        System.out.println("path == " + path);
        File dirPath = new File(path);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        //upfile.getInputStream()
        File file = new File(path, fileName);
        try {
            // 保存文件
            upFile.transferTo(file);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return JsonUtils.createListBean(1000, null, null, fileName);
    }

    @RequestMapping("staffupdate.do")
    public String staffUpdate(Staff staff) {
        staffService.updateStaff(staff);
        return "redirect:/stafflist.html";
    }


    @RequestMapping("staffadd.do")
    public String staffadd(Staff staff) {

        staffService.addStaff(staff);
        return "redirect:/stafflist.html";
    }

    @RequestMapping("staffno.do")
    @ResponseBody
    public LayUIListJsonBean staffNo() {
        String no = staffService.generateIdService();
        System.out.println("id =======" + no);
        return JsonUtils.createListBean(0, null, null, no);
    }

    @RequestMapping("/staffall.do")
    @ResponseBody
    public List<Staff> findAll() {
        return staffService.findAll();
    }

    @RequestMapping("/staffbatch.do")
    public String staffBatch(@RequestParam("mFile") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();
            // 调用工具类中方法，读取excel文件中数据
            List<Staff> sourceList = ExcelUtils.staffExcel(fileName, inputStream);

            // 将对象先转为json格式字符串，然后再转为List<SysUser> 对象
            ObjectMapper objMapper = new ObjectMapper();
            String infos = objMapper.writeValueAsString(sourceList);

            // json字符串转对象
            List<Staff> list = objMapper.readValue(infos, new TypeReference<List<Staff>>() {
            });
            // 批量添加
            staffService.addStaffBatch(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/stafflist.html";
    }

    @RequestMapping("staffdelete.do")
    @ResponseBody
    public JsonBean deleteStaff(String no){
        staffService.deleteStaff(no);
        return JsonUtils.createJsonBean(0,null);
    }

    @RequestMapping("staffSertch.do")
    @ResponseBody
    public JsonBean searchStaff(String no){
        Staff staff = staffService.findStaffByNo(no);
        return JsonUtils.createJsonBean(1,staff);
    }
}

