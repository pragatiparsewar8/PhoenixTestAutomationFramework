package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {
	
	
	@DataProvider(name="LoginAPIDataProvider",parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		
		 return CSVReaderUtil.loadCSV("testData/loginCreds.csv",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobAPIDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {
		Iterator<CreateJobBean> iterator = CSVReaderUtil.loadCSV("testData/createJobData.csv", CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		while(iterator.hasNext()) {
			payloadList.add(CreateJobBeanMapper.mapper(iterator.next()));
		}
		return payloadList.iterator();
	}
	
	
	@DataProvider(name="CreateJobFakeDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> CreateJobFakeDataProvider() {
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(10);
		return payloadIterator;
	}
	
}
