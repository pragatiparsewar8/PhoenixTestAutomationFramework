package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.DashboardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListeners.class)
@Epic("Job Management")
@Feature("Job Count")
public class CountAPITest {

	private	DashboardService dashboardService ;

	
	@BeforeMethod(description = "Initializing the Dashboard service")
	public void setUp() {
		dashboardService = new DashboardService();
	}
	
	
	@Story("Job Count Data is shown correctly")
	@Description( "Verifying if count api is giving correct response")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verify if count api is working correctly",groups= {"api","smoke","regression"})
	public void verifyCountAPIResponse() {
		
		
		dashboardService.count(Role.FD)
				.then()
				.spec(responseSpec_OK())
				.body("message", equalTo("Success"))
				.body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key",
						containsInAnyOrder("created_today", "pending_fst_assignment", "pending_for_delivery"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}

	@Test(description="Verify if count api is giving correct status for invalid token",groups= {"api","smoke","regression","negative"})
	public void countApiTest_MissingAuthToken() throws IOException {
		dashboardService.countWithNoAuth()
		.then()
		.spec(responseSpec_TEXT(401));
	}
}
