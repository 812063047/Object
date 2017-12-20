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
	//ʹ��POI����Excel�ļ�
	@Test
	public void test1() throws FileNotFoundException, IOException {
		String filePath="E:\\heima\\��Ŀһ������BOSϵͳ��58-71�죩\\BOS-day05\\BOS-day05\\����\\ȡ��Ա��������.xls";
		//��װһ��Excel�ļ�
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(filePath)));
		//��ȡ�ļ��е�һ��Sheet��ǩҳ
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
