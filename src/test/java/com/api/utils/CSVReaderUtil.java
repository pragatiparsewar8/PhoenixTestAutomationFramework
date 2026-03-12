package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	
	private CSVReaderUtil() {
		
	}
	
	public static Iterator<UserBean> loadCSV(String pathOfCSVFile) {
		// TODO Auto-generated method stub
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		
		InputStreamReader isr = new InputStreamReader(input);
		CSVReader csvReader = new CSVReader(isr);
		
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		
		List<UserBean> userList = csvToBean.parse();
		
		System.out.println(userList);
		return userList.iterator();
		
	}


}
