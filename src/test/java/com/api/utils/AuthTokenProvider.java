package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private static Map<Role,String> tokenCache = new ConcurrentHashMap<Role,String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

	private AuthTokenProvider() {

	}
	@Step("Getting the Auth token for the role")
	public static String getToken(Role role)  {
		// TODO Auto-generated method stub
		
		LOGGER.info("Checking if the token for {} is present in the cache",role);
		if(tokenCache.containsKey(role)) {
			LOGGER.info("token found for {}",role);
			return tokenCache.get(role);
		}
		
		UserCredentials userCredentials = null;
		LOGGER.info("token Not found making the login requsest for the role {}",role);

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

		LOGGER.info("Token cached for future request");
		tokenCache.put(role, token);
		return token;
	}

}
