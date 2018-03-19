package com.ws.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.ws.connection.ApiConnection;

@Component
public class WeatherCache {

	/*
	 * The innards of this method will only be ran
	 * if an existing object for the passed zip
	 * is not currently found.
	 */
    @Cacheable(value = "weatherCache")
    public WeatherData getZipData(String zipcode) throws Exception {
    	WeatherData zipcodeResult = new Gson().fromJson(ApiConnection.connectToWeatherApi(zipcode), WeatherData.class);
    	zipcodeResult.setZipcode(zipcode);
    	System.out.println("Bean created for: " + zipcodeResult.getZipcode());
        return zipcodeResult;
    }
    
    @CacheEvict(value = "weatherCache", allEntries = true)
    public void removeFromCache() {}
}
