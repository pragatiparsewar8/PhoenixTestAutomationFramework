package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
		
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		
		
		int userNameIndex = -1;
		int passwordIndex = -1;
		
		XSSFRow headersRow = mySheet.getRow(0);
		for(Cell cell:headersRow) {
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}
			
			if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}
		}
		System.out.println();
	}

}
