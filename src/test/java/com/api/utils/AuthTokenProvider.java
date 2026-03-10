package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Role role)  {
		// TODO Auto-generated method stub
		
		UserCredentials userCredentials = null;
		if(role == FD) {
			userCredentials = new UserCredentials("iamfd","password");
		}
		else if(role == SUP) {
			userCredentials = new UserCredentials("iamsup","password");
		}
		else if(role == ENG) {
			userCredentials = new UserCredentials("iameng","password");
		}
		else if(role == QC) {
			userCredentials = new UserCredentials("iamqc","password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).body("message", Matchers.equalTo("Success")).extract().jsonPath()
				.getString("data.token");

		return token;
	}

}
