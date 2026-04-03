package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

import io.qameta.allure.Step;

public class CustomerDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);

	private static final String CUSTOMER_DETAILS_QUERY = """
			Select * from tr_customer where id= ?;
			""";

	@Step("Retriving the Customer Information from DB for the specific customer id")
	public static CustomerDBModel getCustomerInfo(int customerId) {
		CustomerDBModel customerDBModel = null;
		try {
			LOGGER.info("Getting the connection from the Database Manager");

			Connection conn = DatabaseManager.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_DETAILS_QUERY);
			preparedStatement.setInt(1, customerId);
			LOGGER.info("Executing the SQL Query", CUSTOMER_DETAILS_QUERY);

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				customerDBModel = new CustomerDBModel(resultset.getInt("id"), resultset.getString("first_name"),
						resultset.getString("last_name"), resultset.getString("mobile_number"),
						resultset.getString("mobile_number_alt"), resultset.getString("email_id"),
						resultset.getString("email_id_alt"), resultset.getInt("tr_customer_address_id"));
			}
		} catch (Exception e) {
			LOGGER.error("Cannot Convert the ResultSet to the  CustomerDBModel bean", e);

			System.out.println(e.getMessage());
		}
		return customerDBModel;

	}
}
