package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Epic("User Management")
@Feature("Authentication")
public class LoginAPITest extends BaseTest {

	private UserCredentials userCredentials;

	@BeforeMethod(description = "Create the payload for the login api")
	public void setUp() {
		userCredentials = new UserCredentials("iamfd", "password");
	}

	@Story("Valid user should be able to login into the system")
	@Description("Verify if FD user is able to login via API")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if login api is working for FD user", groups = { "api", "smoke", "regression" },
			retryAnalyzer = com.api.retry.RetryAnalyzer.class)
	public void loginAPITest() throws IOException {

		authService.login(userCredentials).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
