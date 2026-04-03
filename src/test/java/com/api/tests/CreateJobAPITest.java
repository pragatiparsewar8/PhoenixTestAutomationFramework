package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.responseSpec_OK;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.Service_Location;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.JobService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListeners.class)
@Epic("Job Management")
@Feature("Job Creation")
public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;
	private JobService jobService;
	
	@BeforeMethod(description="Creating payload for creat job api")
	public void setUp() {
		Customer customer = new Customer("Pragati", "parsewar", "9420425968", "", "pragati@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D404", "Vsanat Galaxy", "Bangur bangur", "inorvit", "Mumbai","413517","India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "190887367343284", "190887367343284", "190887367343284", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problem = new Problems(Problem.POOR_BATTERY_LIFE.getCode(),"BatteryIssue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);
		
		 createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		 jobService = new JobService();
	}

	
	@Story("FD should be able to create job")
	@Description("Verifying if FD is able to use create job api and Inwrranty job is created")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description="Verify if create job api is able to create Inwarranty job",groups= {"api","smoke","regression"})
	public void createJobAPITest() {
		
		
		jobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
		
		
	}
}
