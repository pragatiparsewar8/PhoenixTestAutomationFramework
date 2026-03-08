package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then() 
		.spec(SpecUtil.responseSpec_OK())
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
		.spec(SpecUtil.requestSpec())
		.log().all()
		.when()
		.post("master")
		.then() 
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
