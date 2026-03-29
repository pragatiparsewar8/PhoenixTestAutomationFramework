package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {
	private static final String COUNT_ENDPOINT="/dashboard/count";
	
	public Response count(Role role) {
	Response response = 	given()
		.spec(requestSpecWithAuth(role))
		.when()
				.get(COUNT_ENDPOINT);
		
		return response;
				
	}
	
	
	public Response count() {
		Response response = 	given()
				.spec(requestSpec())
				.when().get(COUNT_ENDPOINT);
			
			return response;
					
		}
}
