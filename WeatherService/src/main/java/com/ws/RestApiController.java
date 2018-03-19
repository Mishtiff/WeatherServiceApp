package com.ws;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ws.cache.WeatherCache;
import com.ws.cache.WeatherData;

@RestController
@RequestMapping("/api")
public class RestApiController implements ErrorController{

    private static final String PATH = "/error";
    
    @Autowired
	WeatherCache weatherCache;
	
	@RequestMapping("/wind/{zipcode}")
	public String queryWeatherWithZipcode(@PathVariable("zipcode") String zipcode) throws IOException{
		try{
			validateZipcode(zipcode);
			WeatherData cachedZipData = weatherCache.getZipData(zipcode);
			return cachedZipData.printWeatherData();
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
	
	@RequestMapping("/wind/bustCache/")
	public String bustCurrentCache() throws IOException{
		weatherCache.removeFromCache();
		return "Cleared the current cache.";
	}
	
	public void validateZipcode(String zipcode) throws Exception{
		if (!zipcode.matches("[0-9]+") || zipcode.length() != 5){
			throw new Exception("invalid zip format.");
		}
	}
	
	@RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
