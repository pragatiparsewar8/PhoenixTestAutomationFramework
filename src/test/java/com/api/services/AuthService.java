package com.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService extends AbstractService {

	private static final String LOGIN_ENDPOINT = "login";

	@Step("Perform login request with the userCredentials")
	public Response login(Object userCredentials) {
		return postWithoutAuth(LOGIN_ENDPOINT, userCredentials);
	}
}
