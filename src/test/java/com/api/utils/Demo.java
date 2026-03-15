package com.api.utils;

import java.util.Iterator;

import com.dataproviders.api.bean.CreateJobBean;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Iterator<CreateJobBean> it = CSVReaderUtil.loadCSV("testData/createJobData.csv", CreateJobBean.class);
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
