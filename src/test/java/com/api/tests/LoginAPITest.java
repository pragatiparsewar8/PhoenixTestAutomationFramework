package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListeners.class)
public class LoginAPITest {

	private UserCredentials userCredentials;
	private AuthService authService;

	@BeforeMethod(description = "Create the payload for the login api")
	public void setUp() {
		userCredentials = new UserCredentials("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verifying if login api is working for FD user", groups = { "api", "smoke", "regression" })
	public void loginAPITest() throws IOException {

		authService.login(userCredentials).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
