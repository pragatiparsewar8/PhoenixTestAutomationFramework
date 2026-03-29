package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.AuthService;
import com.api.services.UserService;

public class UserDetailsAPITest {
	private UserService userService ;
	
	@BeforeMethod(description = "Initializing the user service")
	public void setUp() {
		userService = new UserService();
	}
	
	
	
	@Test(description = "Verify if user details api response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void userDetailsApiTest() {
		
		
		userService.userDetails(Role.FD).then().spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}
