package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public abstract class AbstractService {

	protected final Logger logger = LogManager.getLogger(getClass());

	protected Response get(Role role, String endpoint) {
		logger.info("GET {} for role {}", endpoint, role);
		return given().spec(SpecUtil.requestSpecWithAuth(role)).when().get(endpoint);
	}

	protected Response getWithoutAuth(String endpoint) {
		logger.info("GET {} without auth", endpoint);
		return given().spec(SpecUtil.requestSpec()).when().get(endpoint);
	}

	protected Response post(Role role, String endpoint) {
		logger.info("POST {} for role {}", endpoint, role);
		return given().spec(SpecUtil.requestSpecWithAuth(role)).when().post(endpoint);
	}

	protected Response post(Role role, String endpoint, Object payload) {
		logger.info("POST {} for role {} with payload {}", endpoint, role, payload);
		return given().spec(SpecUtil.requestSpecWithAuth(role, payload)).when().post(endpoint);
	}

	protected Response postWithBody(Role role, String endpoint, Object payload) {
		logger.info("POST {} for role {} with payload {}", endpoint, role, payload);
		return given().spec(SpecUtil.requestSpecWithAuth(role)).body(payload).when().post(endpoint);
	}

	protected Response postWithoutAuth(String endpoint, Object payload) {
		logger.info("POST {} without auth with payload {}", endpoint, payload);
		return given().spec(SpecUtil.requestSpec(payload)).when().post(endpoint);
	}
}
