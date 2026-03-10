package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {

	@Test(description = "Verify if user details api response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void userDetailsApiTest() {

		given().spec(requestSpecWithAuth(FD)).when().get("userDetails").then().spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}
