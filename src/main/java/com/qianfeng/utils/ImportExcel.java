package com.qianfeng.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportExcel {

	// 使用正则表达式匹配后缀类型(\\:前一个\是转义字符；\?i：忽略大小写)
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

	public static List<Map<String, Object>> readExcel(String fileName, InputStream inputStream) throws IOException {

//		// 通过据字符串截取方式获取文件名后缀
//		String suffix = fileName.substring(fileName.indexOf(".") + 1);
//
//		// 用接口方式接收数据（类似工厂模式思想，具体的实现类由下面决定）
//		Workbook workbook = null;
//		// 判断文件后缀类型（忽略大小写）
//		if (suffix.equalsIgnoreCase("xls")) {
//			workbook = new HSSFWorkbook(inputStream);
//		} else {
//			workbook = new XSSFWorkbook(inputStream);
//		}

		boolean ret = isXls(fileName);
		Workbook workbook = null;
		// 根据正则判断文件后缀类型
		if (ret) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			workbook = new XSSFWorkbook(inputStream);
		}

		Sheet sheet = workbook.getSheetAt(0);
		
		// 得到标题行对象（即类属性名行对象）
		Row titleRow = sheet.getRow(0);
		
		// 表中的数据行列格式固定，在这里读取到行数和列数，为循环遍历提供数据
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = titleRow.getLastCellNum();
		
		// 准备一个通用类型集合，map中：key值为表中每列标题（与类属性名一致）；value值为对应的数据；
		List<Map<String, Object>> list = new ArrayList<>();

		// 从第一行（标题行）后开始遍历读取
		for (int i = 1; i <= lastRowNum; i++) {
			
			// 将每行数据放在map中，key值为表中每列标题（与类属性名一致）；value值为对应的数据；
			Map<String, Object> map = new HashMap<String, Object>();
			
			Row row = sheet.getRow(i);
			
			for (int j = 0; j < lastCellNum; j++) {
				// 获取标题行中的每列标题名 即属性名，作为map中的key值
				String key = titleRow.getCell(j).getStringCellValue();
				// 获取每行中的单元格对象
				Cell cell = row.getCell(j);
				// 将单元格数据类型统一设为String类型，便于处理
				cell.setCellType(CellType.STRING);
				
				// cell.getStringCellValue():获取每个单元格中的数据
				map.put(key, cell.getStringCellValue());
			}
			// 将每行生成的map添加到list集合中
			list.add(map);
		}

		workbook.close();
		
		return list;
	}

}
