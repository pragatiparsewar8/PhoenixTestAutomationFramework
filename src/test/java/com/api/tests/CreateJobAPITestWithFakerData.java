package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakerData {

	private CreateJobPayload createJobPayload;
	private JobService jobService;
	public static final String COUNTRY = "INDIA";

	@BeforeMethod(description = "Creating payload for creat job api")
	public void setUp() {

		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
		 jobService = new JobService();
	}

	@Test(description = "Verify if create job api is able to create Inwarranty job", groups = { "api", "smoke",
			"regression" })
	public void createJobAPITest() {

		int customerId = jobService.createJob(Role.FD, createJobPayload).then()
				.spec(responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().body().jsonPath()
				.getInt("data.tr_customer_id");

		Customer expectedCustomerData = createJobPayload.customer();
		CustomerDBModel actualCustomerDataInDB = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(actualCustomerDataInDB.getFirst_name(), expectedCustomerData.first_name());
		Assert.assertEquals(actualCustomerDataInDB.getLast_name(), expectedCustomerData.last_name());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number(), expectedCustomerData.mobile_number());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id(), expectedCustomerData.email_id());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id_alt(), expectedCustomerData.email_id_alt());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number_alt(), expectedCustomerData.mobile_number_alt());

		int customerAddressId = actualCustomerDataInDB.getTr_customer_address_id();

		CustomerAddressDBModel customerAddressFromDB = CustomerAddressDao.getCustomerAddress(customerAddressId);
		Assert.assertEquals(customerAddressFromDB.getFlat_number(), createJobPayload.customer_address().flat_number());

		Assert.assertEquals(customerAddressFromDB.getApartment_name(), createJobPayload.customer_address().apartment_name());
		Assert.assertEquals(customerAddressFromDB.getArea(), createJobPayload.customer_address().area());
		Assert.assertEquals(customerAddressFromDB.getLandmark(), createJobPayload.customer_address().landmark());
		Assert.assertEquals(customerAddressFromDB.getState(), createJobPayload.customer_address().state());
		Assert.assertEquals(customerAddressFromDB.getStreet_name(), createJobPayload.customer_address().street_name());
		Assert.assertEquals(customerAddressFromDB.getCountry(), createJobPayload.customer_address().country());
		Assert.assertEquals(customerAddressFromDB.getPincode(), createJobPayload.customer_address().pincode());

	}
}
