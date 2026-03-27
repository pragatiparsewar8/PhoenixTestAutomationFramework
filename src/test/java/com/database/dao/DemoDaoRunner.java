package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CustomerDBModel data = CustomerDao.getCustomerInfo(226575);
		System.out.println(data);
		CustomerAddressDBModel cadm = CustomerAddressDao.getCustomerAddress(228952);
		System.out.println(cadm);
		CustomerProductDBModel cpdm = CustomerProductDao.getProductInfoFromDB(228950);
		System.out.println(cpdm);
	}

}
