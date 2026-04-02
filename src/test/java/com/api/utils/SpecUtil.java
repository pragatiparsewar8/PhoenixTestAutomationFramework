package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static org.hamcrest.Matchers.lessThan;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	@Step("Setting up the BaseURI , Content Type as Application/JSON and attaching the SensitiveData Filter")
	public static RequestSpecification requestSpec() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}
	
	@Step("Setting up the BaseURI , Content Type as Application/JSON and attaching the SensitiveData Filter")
	public static RequestSpecification requestSpec(Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}
	
	
	@Step("Setting up the BaseURI , Content Type as Application/JSON and attaching the SensitiveData Filter for a role")
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}
	
	@Step("Setting up the BaseURI , Content Type as Application/JSON and attaching the SensitiveData Filter for a role and attaching payload")
	public static RequestSpecification requestSpecWithAuth(Role role,Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}
	
	
	@Step("Expecting the response to have Content Type as Application/JSON, Status 200 and Response Time Less than 2500 ms")
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(lessThan(2500L))
		.build();
		
		return responseSpecification;
	}
	
	@Step("Expecting the response to have Content Type as Application/JSON and Response Time Less than 2500 ms and status code")
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(2500L))
		.build();
		
		return responseSpecification;
	}
	
	@Step("Expecting the response to have Content Type as Text and Response Time Less than 2500 ms and status code")
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(2500L))
		.build();
		
		return responseSpecification;
	}
}
