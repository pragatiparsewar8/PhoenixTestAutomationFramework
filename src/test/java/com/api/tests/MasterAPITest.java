package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.MasterService;
@Listeners(com.listeners.APITestListeners.class)
public class MasterAPITest {
	private MasterService masterService;

	@BeforeMethod(description = "Instantiating the Master Service Object")
	public void setup() {
		masterService = new MasterService();
	}
	@Test(description="Verify if Master api is working correctly",groups= {"api","smoke","regression"})
	public void masterAPITest() {
		masterService.master(FD)
		.then() 
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body("data",notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_oem.id",everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test(description="Verify if Master api is giving correct status for invalid token",groups= {"api","smoke","regression","negative"})
	public void invalidTokenMasterAPIRequest() {
		given()
		.spec(requestSpec())
		.log().all()
		.when()
		.post("master")
		.then() 
		.spec(responseSpec_TEXT(401));
	}
}
