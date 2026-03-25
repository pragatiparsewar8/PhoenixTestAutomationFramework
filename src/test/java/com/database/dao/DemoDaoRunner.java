package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CustomerDBModel data = CustomerDao.getCustomerInfo();
		System.out.println(data);
	}

}
