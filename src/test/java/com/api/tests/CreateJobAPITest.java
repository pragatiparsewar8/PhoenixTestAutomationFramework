package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Pragati", "parsewar", "9420425968", "", "pragati@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D404", "Vsanat Galaxy", "Bangur bangur", "inorvit", "Mumbai","413517","India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-09-08T18:30:00.000Z", "290987367343284", "290987367343284", "290987367343284", "2025-09-08T18:30:00.000Z", 1, 1);
		Problems problem = new Problems(1,"BatteryIssue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
		
		
	}
}
