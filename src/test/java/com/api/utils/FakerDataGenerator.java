package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private static final String COUNTRY = "INDIA";
	private static final Random RANDOM = new Random();

	private static final int mst_service_location_id = 0;
	private static final int mst_platform_id = 2;
	private static final int mst_warrenty_status_id = 1;
	private static final int mst_oem_id = 1;
	private static final int product_id = 1;
	private static final int model_id = 1;

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct product = generateFakeCustomerProduct();
		List<Problems> problemList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(mst_service_location_id, mst_platform_id, mst_warrenty_status_id, mst_oem_id, customer, customerAddress, product, problemList);
		return payload;
	}
	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for(int i =1;i<= count ;i++) {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct product = generateFakeCustomerProduct();
		List<Problems> problemList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(mst_service_location_id, mst_platform_id, mst_warrenty_status_id, mst_oem_id, customer, customerAddress, product, problemList);
		payloadList.add(payload);
		}
		return payloadList.iterator();
	}

	private static List<Problems> generateFakeProblemsList() {
		// TODO Auto-generated method stub
		String remark = faker.lorem().sentence(3);

		int problemId = RANDOM.nextInt(26) + 1;

		Problems problem = new Problems(problemId, remark);

		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {
		// TODO Auto-generated method stub
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct product = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl,
				product_id, model_id);
		
		return product;
	}

	private static CustomerAddress generateFakeCustomerAddressData() {
		// TODO Auto-generated method stub
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("###");

		String state = faker.address().state();

		CustomerAddress address = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pinCode,
				COUNTRY, state);

		return address;
	}

	private static Customer generateFakeCustomerData() {
		// TODO Auto-generated method stub
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("94########");
		String altMobNumer = faker.numerify("94########");
		String email = faker.internet().emailAddress();
		String altEmail = faker.internet().emailAddress();

		Customer customer = new Customer(fName, lName, mobileNumber, altMobNumer, email, altEmail);

		return customer;
	}
}
