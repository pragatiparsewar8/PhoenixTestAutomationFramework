package com.api.services;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class MasterService extends AbstractService {

	private static final String MASTER_ENDPOINT = "/master";

	@Step("Making Master API Request")
	public Response master(Role role) {
		return post(role, MASTER_ENDPOINT);
	}
}
