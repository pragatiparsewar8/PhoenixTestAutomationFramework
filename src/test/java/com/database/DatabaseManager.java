package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	private static final String DB_URL = EnvUtil.getValue("DB_URL");
	private static final String DB_USERNAME =EnvUtil.getValue("DB_USERNAME");
	private static final String DB_PASSWORD = EnvUtil.getValue("DB_PASSWORD");
	private static final int CONNECTION_TIMEOUT = Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT"));
	private static final int MAXIMUM_POOLSIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOLSIZE"));
	private static final int IDLE_TIMEOUT = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT"));
	private static final int MINIMUM_IDLE = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE"));
	private static final int MAXLIFE_TIMEOUT = Integer.parseInt(ConfigManager.getProperty("MAXLIFE_TIMEOUT"));
	private static final String POOL_NAME = ConfigManager.getProperty("POOL_NAME");
	
	private volatile static Connection conn;
	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;

	private DatabaseManager() {

	}

	private synchronized static void initializePool() {

		if (conn == null) {

			synchronized (DatabaseManager.class) {
				if (conn == null) {
					HikariConfig hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOLSIZE);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE);
					hikariConfig.setMaxLifetime(MAXLIFE_TIMEOUT);
					hikariConfig.setPoolName(POOL_NAME);
					
					hikariDataSource = new HikariDataSource(hikariConfig);
				}
			}
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
		if(hikariDataSource == null) {
			initializePool();
		}
		else if(hikariDataSource.isClosed()) {
			throw new SQLException("Hikari data source is closed");
		}
		
			 connection = hikariDataSource.getConnection();
		
		return connection;
	}
}
