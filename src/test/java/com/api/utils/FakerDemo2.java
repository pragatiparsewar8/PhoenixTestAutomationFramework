package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	
	
	public static final String COUNTRY = "INDIA";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker(new Locale("en-IND"));
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("94########");
		String altMobNumer = faker.numerify("94########");
		String email = faker.internet().emailAddress();
		String altEmail = faker.internet().emailAddress();

//		Customer customer = new Customer(fName,lName,mobileNumber,altMobNumer,email,altEmail);

		Customer customer = new Customer(fName, lName, mobileNumber, altMobNumer, email, altEmail);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("###");
		
		String state = faker.address().state();
		

		CustomerAddress address = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pinCode,
				COUNTRY, state);
			
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();
		
		CustomerProduct product = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, 1, 1);
		System.out.println(product);
		
		String remark = faker.lorem().sentence(3);
		Random random = new Random();
		int problemId = random.nextInt(26)+1;
		
		Problems problem = new Problems(problemId,remark);
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);
		
		CreateJobPayload createjobPayload = new CreateJobPayload(0, 2, 2, 2, customer, address, product, problemList);
		
		System.out.println(createjobPayload);
	}

}
