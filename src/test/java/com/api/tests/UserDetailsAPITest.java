package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsApiTest()  {
		
		Header authHeader = new Header("Authorization",getToken(FD));
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.header(authHeader)
			.accept(ContentType.JSON)
		.when()
			.get("userDetails")
		.then()
			.statusCode(200)
			.time(lessThan(3500L))
			.and()
			.log().all()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
			
			
	}
}
