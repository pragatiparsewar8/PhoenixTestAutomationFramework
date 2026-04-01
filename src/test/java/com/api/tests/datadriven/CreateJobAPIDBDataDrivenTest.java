package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListeners.class)
public class CreateJobAPIDBDataDrivenTest {
	
	
	@Test(description="Verify if create job api is able to create Inwarranty job",groups= {"api","dataDriven","regression","csv"},
			dataProviderClass=com.dataproviders.DataProvidersUtils.class,
			dataProvider="CreateJobAPIDBDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		
		
		given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
		
		
	}
}
