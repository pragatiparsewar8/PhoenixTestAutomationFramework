package com.api.tests.datadriven;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.DataProvidersUtils;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListeners.class)
public class LoginAPIJsonDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Initializing the auth service")
	public void setUp() {
		authService = new AuthService();
	}
	

	@Test(description = "Verifying if login api is working for FD user",
			groups = { "api", "smoke", "regression" },
			dataProviderClass = DataProvidersUtils.class,
			dataProvider = "LoginAPIJsonDataProvider"
	)
	public void loginAPIJsonDataDrivenTest(UserCredentials userCredentials) {

		authService.login(userCredentials).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
