package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	private static final String CUSTOMER_DETAILS_QUERY="""
			Select * from tr_customer where id=226575;
			""";
	
	
	public static CustomerDBModel getCustomerInfo() throws SQLException {
		Connection conn = DatabaseManager.getConnection();
		Statement statement = conn.createStatement();
		ResultSet resultset = statement.executeQuery(CUSTOMER_DETAILS_QUERY);
		CustomerDBModel customerDBModel = null ;
		while(resultset.next()) {
			System.out.println("Inside loop");
			System.out.println(resultset.getString("first_name"));
			
			 customerDBModel = new CustomerDBModel(resultset.getString("first_name"),
					resultset.getString("last_name"),resultset.getString("mobile_number"),
					resultset.getString("mobile_number_alt"),resultset.getString("email_id"),
					resultset.getString("email_id_alt"));
		}
		return customerDBModel;
		
	}
}
