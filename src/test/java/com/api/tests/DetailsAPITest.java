package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Detail;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest extends BaseTest {

	private Detail detailPayload;

	@BeforeMethod(description = "Creating detail payload")
	public void setup() {
		detailPayload = new Detail("created_today");
	}

	@Story("Job Details is shown correctly for FD")
	@Description("Verify if Details API is working properly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if Details API is working properly", groups = { "api", "smoke", "e2e" })

	public void detailAPITest() {
		dashboardService.details(Role.FD, detailPayload).then().spec(responseSpec_OK()).body("message",
				equalTo("Success"));
	}

}
