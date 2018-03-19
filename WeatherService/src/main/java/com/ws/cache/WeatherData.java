package com.ws.cache;

import java.io.Serializable;

public class WeatherData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String zipcode;
	Coord coord;
	Weather[] weather;
	String base;
	Main main;
	Integer visibility;
	Wind wind;
	Clouds clouds;
	Integer dt;
	Sys sys;
	Integer id;
	String name;
	String cod;
	String message;
	
	/*
	 * if the windSpeed or the windDirection are null, it means that OpenWeatherMap API did not return a value.
	 * I found cases where the API was not returning a wind "deg" value, however wind speed seemed to always be present.
	 */
    public String printWeatherData() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("Wind Speed: ");
    	sb.append(wind.speed);
    	sb.append(System.getProperty("line.separator"));
    	sb.append("Wind Direction: ");
    	sb.append(wind.deg);
    	
        return sb.toString();
    }
    
    public boolean checkCodResult(){
    	if(cod.equals("404")){
    		return false;
    	}
    	
    	return true;
    }
    
    public String getErrorMessage(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(message);
    	sb.append(" for: ");
    	sb.append(zipcode);
    	return sb.toString();
    }

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

/*
 * These are the custom classes created to automatically read
 * all of the data from JSON returned from the API. 
 * Unfortunately I was unable to find the return data types 
 * for each variable so I have done my best to guess each one.
 */
class Coord implements Serializable{
	private static final long serialVersionUID = -8672988437485614580L;
	double lon;
	double lat;
}

class Weather implements Serializable{
	private static final long serialVersionUID = -6340119906071489489L;
	Integer id;
	String main;
	String description;
	String icon;
}

class Main implements Serializable{
	private static final long serialVersionUID = 5530227783090231873L;
	double temp;
	Integer pressure;
	Integer humidity;
	double temp_min;
	double temp_max;
}

class Wind implements Serializable{
	private static final long serialVersionUID = 2410864990996249774L;
	double speed;
	double deg = 0;
}

class Clouds implements Serializable{
	private static final long serialVersionUID = -6450450118912535794L;
	Integer all;
}

class Sys implements Serializable{
	private static final long serialVersionUID = 6885185472790795507L;
	Integer type;
	Integer id;
	double message;
	String country;
	Integer sunrise;
	Integer sunset;
}