package com.api.services;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardService extends AbstractService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";

	@Step("Making Count API Request for the role")
	public Response count(Role role) {
		return get(role, COUNT_ENDPOINT);
	}

	@Step("Making Count API Request without Auth token")
	public Response countWithNoAuth() {
		return getWithoutAuth(COUNT_ENDPOINT);
	}

	@Step("Making Details API Request")
	public Response details(Role role, Object payload) {
		return postWithBody(role, DETAIL_ENDPOINT, payload);
	}
}
