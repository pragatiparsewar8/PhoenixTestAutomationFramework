package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static org.hamcrest.Matchers.lessThan;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	public static RequestSpecification requestSpec() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}
	
	public static RequestSpecification requestSpec(Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}
	
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role,Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}
	
	
	
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
}
