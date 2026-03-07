package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class CountAPITest {

	@Test
	public void verifyCountAPIResponse()  {
		given().baseUri(getProperty("BASE_URI")).and()
				.header("Authorization", getToken(FD)).when().get("/dashboard/count").then()
				.log().all().statusCode(200).body("message", equalTo("Success")).time(lessThan(2500L))
				.body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("created_today","Pending for FST assignment","Pending for delivery"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void countApiTest_MissingAuthToken() throws IOException {
		given().baseUri(getProperty("BASE_URI")).and()
		.when().get("/dashboard/count").then()
		.log().all().statusCode(400);
	}
}
