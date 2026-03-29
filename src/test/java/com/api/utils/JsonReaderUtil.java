package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtil.class);
	public static <T> Iterator<T> loadJson(String filename,Class<T[]> clazz)  {
		LOGGER.info("Reading the JSON from the file {}", fileName);
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

		ObjectMapper objectMapper = new ObjectMapper();

		
		
		T[] classArray;
		List<T> list = null;;
		try {
			LOGGER.info("Converting the JSON Data to the bean class {}", clazz);
			classArray = objectMapper.readValue(input, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			LOGGER.error("Cannot read the json from the file {}", fileName,e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list.iterator();

	}
}
