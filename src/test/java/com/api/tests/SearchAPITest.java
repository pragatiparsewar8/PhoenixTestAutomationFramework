package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Search;
import com.api.utils.SpecUtil;

public class SearchAPITest extends BaseTest {

	private static final String JOB_NUMBER = "JOB_131343";
	private Search searchPayload;

	@BeforeMethod(description = "Creating the search payload")
	public void setup() {
		searchPayload = new Search(JOB_NUMBER);
	}

	@Test(description = "Verify if the search api is working properly", groups = { "e2e", "smoke", "api" })
	public void searchAPITest() {
		jobService.search(Role.FD, searchPayload).then().spec(SpecUtil.responseSpec_OK()).body("message",
				Matchers.equalTo("Success"));
	}

}
