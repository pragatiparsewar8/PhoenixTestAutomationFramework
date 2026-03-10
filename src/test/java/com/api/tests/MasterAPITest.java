package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test(description="Verify if Master api is working correctly",groups= {"api","smoke","regression"})
	public void masterAPITest() {
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("master")
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
