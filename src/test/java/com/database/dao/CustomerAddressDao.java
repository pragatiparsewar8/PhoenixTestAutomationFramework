package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {

	private static final String CUSTOMER_ADDRESS_QUERY ="""
			Select
					id,
					flat_number,
					apartment_name,
					street_name,
					landmark,
					area,
					pincode,
					country,
					state
			from tr_customer_address
			where id = ?
			""";
	
	private CustomerAddressDao() {
		
	}
	public static CustomerAddressDBModel getCustomerAddress(int customerAddressId) {
		CustomerAddressDBModel customerAddressDBModel =null;
		try {
		Connection conn = DatabaseManager.getConnection();
		PreparedStatement prepareStatement = conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
		prepareStatement.setInt(1, customerAddressId);
		ResultSet rs = prepareStatement.executeQuery();
		
		while(rs.next()) {
			 customerAddressDBModel = new CustomerAddressDBModel(rs.getInt("id"), rs.getString("flat_number"), rs.getString("apartment_name"),
							rs.getString("street_name"), rs.getString("landmark"), rs.getString("area"),
							rs.getString("pincode"), rs.getString("country"), rs.getString("state"));
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return customerAddressDBModel;
	}
}
