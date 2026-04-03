package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListeners.class)
@Epic("User Management")
@Feature("User Details")

public class UserDetailsAPITest {
	private UserService userService ;
	
	@BeforeMethod(description = "Initializing the user service")
	public void setUp() {
		userService = new UserService();
	}
	
	
	@Story("UserDetails should be shown")
	@Description("Verify if the Userdetails API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if user details api response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void userDetailsApiTest() {
		
		
		userService.userDetails(Role.FD).then().spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}
