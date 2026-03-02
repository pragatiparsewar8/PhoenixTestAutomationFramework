package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() {
//		REst Assured code
		UserCredentials userCredentials = new UserCredentials("iamfd","password");
		given()
			.baseUri("http://64.227.160.186:9000/v1/")
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.JSON)
			.and()
			.body(userCredentials)
			.log().uri()
			.log().method()
			.log().body()
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.time(lessThan(3500L))
			.and()
			.body("message",equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
			
		
	}
}
