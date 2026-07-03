
package com.api.services;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService extends AbstractService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";

	@Step("Creating Inwarranty Job with Create Job API")
	public Response createJob(Role role, CreateJobPayload createJobPayload) {
		return post(role, CREATE_JOB_ENDPOINT, createJobPayload);
	}

	@Step("Making search api request")
	public Response search(Role role, Object payload) {
		return postWithBody(role, SEARCH_ENDPOINT, payload);
	}
}
