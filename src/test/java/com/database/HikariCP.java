package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {
		
	
	public static void main(String[] args) throws SQLException {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaxLifetime(180000);
		hikariConfig.setPoolName("Phoenix test automation framework pool");
		
		HikariDataSource ds = new HikariDataSource(hikariConfig);
		Connection conn = ds.getConnection();
		
		Statement statement = conn.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select first_name ,last_name ,mobile_number  from tr_customer;");
		
//		while(resultSet.next()) {
//			String first_name = resultSet.getString("first_name");
//			String last_name = resultSet.getString("last_name");
//			String mobile_number = resultSet.getString("mobile_number");
//			System.out.println(first_name + " || " + last_name + " || "+mobile_number);
//		}
		
		ds.close();
	}
}
