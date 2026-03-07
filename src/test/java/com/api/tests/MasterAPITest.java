package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(FD))
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then() 
		.log().all()
		.statusCode(200)
		.time(lessThan(2500L))
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body("data",notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_oem.id",everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test
	public void invalidTokenMasterAPIRequest() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then() 
		.log().all()
		.statusCode(401);
	}
}
