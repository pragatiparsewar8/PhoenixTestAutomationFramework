package com.api.services;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserService extends AbstractService {

	private static final String USERDETAILS_ENDPOINT = "/userDetails";

	@Step("Making UserDetails API Request")
	public Response userDetails(Role role) {
		return get(role, USERDETAILS_ENDPOINT);
	}
}
