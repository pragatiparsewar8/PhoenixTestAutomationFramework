package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		DatabaseManager.createConnection();
		Connection conn = DatabaseManager.getConnection();
		System.out.println(conn);
	}

}
