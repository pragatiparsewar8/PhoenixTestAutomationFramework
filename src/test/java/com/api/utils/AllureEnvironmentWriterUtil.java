package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {
	private static final Logger LOGGER  = LogManager.getLogger(AllureEnvironmentWriterUtil.class);
	public static void createEnvironmentPropertiesFile()  {
		
		// TODO Auto-generated method stub
		String folderPath = "target/allure-reports"; 
		File file = new File(folderPath);
		file.mkdirs();
		
		Properties prop = new Properties();
		prop.setProperty("Name", "Pragati");
		prop.setProperty("Project Name", "Phoenix test automation ");
		prop.setProperty("Env", ConfigManager.env);
		prop.setProperty("Base_URI", "http://64.227.160.186:9000/v1/");
		prop.setProperty("Operating System", System.getProperty("os.name"));
		prop.setProperty("Operating System Version ", System.getProperty("os.version"));
		prop.setProperty("Java Version ", System.getProperty("java.version"));

		
		
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath + "/environment.properties");
			prop.store(fw, "My properties file");
			LOGGER.info("Created the enviroment.properties file at {}",folderPath );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Unable to create the environment.properties file", e);
		}
		
	}

}
