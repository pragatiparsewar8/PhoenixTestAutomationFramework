package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.DataProvidersUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIJsonDataDrivenTest {

	
	

	@Test(description = "Verifying if login api is working for FD user",
			groups = { "api", "smoke", "regression" },
			dataProviderClass = DataProvidersUtils.class,
			dataProvider = "LoginAPIJsonDataProvider"
	)
	public void loginAPIJsonDataDrivenTest(UserCredentials userCredentials) {

		given().spec(SpecUtil.requestSpec(userCredentials))

				.when().post("login").then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
