package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Pragati", "parsewar", "9420425968", "", "pragati@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D404", "Vsanat Galaxy", "Bangur bangur", "inorvit", "Mumbai","413517","India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-09-08T18:30:00.000Z", "290987367343280", "290987367343280", "290987367343280", "2025-09-08T18:30:00.000Z", 1, 1);
		Problems problem = new Problems(1,"BatteryIssue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problem;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
		
		
		
		
	}
}
