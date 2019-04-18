package com.qianfeng.utils;

import com.qianfeng.entity.Staff;
import com.qianfeng.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static boolean isXls(String fileName) {
        // (?i)忽略大小写
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            return true;
        } else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return false;
        } else {
            throw new RuntimeException("格式不对");
        }
    }

    public static List<Staff> staffExcel(String fileName, InputStream inputStream) throws Exception {
        Workbook workbook = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Staff> list = new ArrayList<>();

        if (isXls(fileName)) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }

        // 得到Sheet
        Sheet sheet = workbook.getSheetAt(0);

        // 获取行数
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
            Row hr = sheet.getRow(i);
            Staff staff = new Staff();
            staff.setNo(hr.getCell(0).getStringCellValue());
            staff.setName(hr.getCell(1).getStringCellValue());
            staff.setDid((int) hr.getCell(2).getNumericCellValue());
            staff.setFlag(1);
            staff.setSex(hr.getCell(3).getStringCellValue());
            staff.setPhone(new Double(hr.getCell(4).getNumericCellValue()).intValue() + "");
            staff.setEmail(hr.getCell(5).getStringCellValue());
            staff.setQq(new Double(hr.getCell(6).getNumericCellValue()).intValue() + "");
            hr.getCell(7).setCellType(CellType.STRING);
            staff.setCreatedate(format.parse(hr.getCell(7).getStringCellValue()));
            list.add(staff);
        }

        workbook.close();
        return list;

    }


    public static List<Student> studentExcel(String fileName, InputStream inputStream) throws Exception {
        Workbook workbook = null;
        List<Student> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (isXls(fileName)) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }

        // 得到Sheet
        Sheet sheet = workbook.getSheetAt(0);

        // 获取行数
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
            Row hr = sheet.getRow(i);
            Student student = new Student();
            student.setNo(hr.getCell(0).getStringCellValue());
            student.setName(hr.getCell(1).getStringCellValue());
            student.setGid((int) hr.getCell(2).getNumericCellValue());
            student.setSex(hr.getCell(3).getStringCellValue());
            student.setPhone(new Double(hr.getCell(4).getNumericCellValue()).intValue() + "");
            student.setQq(new Double(hr.getCell(5).getNumericCellValue()).intValue() + "");
            student.setEmail(hr.getCell(6).getStringCellValue());
            student.setCardno(hr.getCell(7).getStringCellValue());
            student.setSchool(hr.getCell(8).getStringCellValue());
            student.setEducation(hr.getCell(9).getStringCellValue());
            student.setIntrono(hr.getCell(10).getStringCellValue());
            student.setBirthday(sdf.parse(hr.getCell(11).getStringCellValue()));
            student.setCreatedate(sdf.parse(hr.getCell(12).getStringCellValue()));
            list.add(student);
        }

        workbook.close();
        return list;

    }
}
