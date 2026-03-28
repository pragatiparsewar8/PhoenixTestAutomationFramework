package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	private static final String CUSTOMER_DETAILS_QUERY="""
			Select * from tr_customer where id= ?;
			""";
	
	
	public static CustomerDBModel getCustomerInfo(int customerId)  {
		CustomerDBModel customerDBModel = null ;
		try {
		Connection conn = DatabaseManager.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_DETAILS_QUERY);
		preparedStatement.setInt(1, customerId);
		ResultSet resultset = preparedStatement.executeQuery();
		
		while(resultset.next()) {
			
			 customerDBModel = new CustomerDBModel(resultset.getInt("id"),resultset.getString("first_name"),
					resultset.getString("last_name"),resultset.getString("mobile_number"),
					resultset.getString("mobile_number_alt"),resultset.getString("email_id"),
					resultset.getString("email_id_alt"),resultset.getInt("tr_customer_address_id"));
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return customerDBModel;
		
	}
}
