package com.ws.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.io.IOUtils;

public class ApiConnection {

	private static final String WEATHER_URL_BY_ZIP = setUrlApiFromProp();
	private static String apiKey = setApiKeyFromProp();
	
	public static String connectToWeatherApi(String zipcode) throws Exception {
		String URLWithData = String.format(WEATHER_URL_BY_ZIP, new Object[]{zipcode, apiKey});
		String zipQuery;
		
		try{
			URL obj = new URL(URLWithData);
			zipQuery = IOUtils.toString(obj.openStream());
		}
		catch(FileNotFoundException FNFE){
			throw new Exception("Zipcode not found for: " + zipcode);
		} 
		catch (MalformedURLException e) {
			throw new Exception("URL format error");
		} 
		catch (IOException e) {
			throw new Exception("Error reading API response");
		}
		
		return zipQuery;
	}
	
	/*
	 * Grab the apiKey from the properties file.
	 */
	private static String setApiKeyFromProp() {
		Properties prop = new Properties();
	    InputStream input = null;

        try {
    		input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException e) {
			System.out.println("Failed to read apiKey from properties file.");
		}
        return prop.getProperty("apiKey");
	}
	
	/*
	 * Grab the API Url from the properties file.
	 */
	private static String setUrlApiFromProp() {
		Properties prop = new Properties();
	    InputStream input = null;

        try {
    		input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException e) {
			System.out.println("Failed to read apiKey from properties file.");
		}
        return prop.getProperty("apiURLString");
	}
}
