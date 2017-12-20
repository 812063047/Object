package com.xzq.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.xml.resolver.apps.resolver;
import org.junit.Test;

public class POITest {
	//使用POI解析Excel文件
	@Test
	public void test1() throws FileNotFoundException, IOException {
		String filePath="E:\\heima\\项目一：物流BOS系统（58-71天）\\BOS-day05\\BOS-day05\\资料\\取派员导入数据.xls";
		//包装一个Excel文件
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(filePath)));
		//读取文件中第一个Sheet标签页
		HSSFSheet hssfSheet= workbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			int rowNum=row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			System.out.println();
			for (Cell cell : row) {
				String value=cell.getStringCellValue();
				System.err.print(value + " ");
			}
		}
	}
}
