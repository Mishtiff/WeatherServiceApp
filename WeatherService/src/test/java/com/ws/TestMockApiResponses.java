package com.ws;

import static org.junit.Assert.*;

import org.junit.Test;
import com.google.gson.Gson;
import com.ws.cache.WeatherData;

public class TestMockApiResponses {
	
	private final String[] mockZipData = {"{\"coord\":{\"lon\":-92.03,\"lat\":32.56},\"weather\":[{\"id\":721,\"main\":\"Haze\",\"description\":\"haze\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":299.15,\"pressure\":1010,\"humidity\":54,\"temp_min\":299.15,\"temp_max\":299.15},\"visibility\":16093,\"wind\":{\"speed\":3.6,\"deg\":190},\"clouds\":{\"all\":75},\"dt\":1521236100,\"sys\":{\"type\":1,\"id\":1164,\"message\":0.0039,\"country\":\"US\",\"sunrise\":1521202547,\"sunset\":1521245850},\"id\":420014897,\"name\":\"Leisure Village\",\"cod\":200}",
										  "{\"coord\":{\"lon\":-73.75,\"lat\":40.79},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":275.15,\"pressure\":1012,\"humidity\":37,\"temp_min\":274.15,\"temp_max\":276.15},\"visibility\":16093,\"wind\":{\"speed\":9.3,\"deg\":310,\"gust\":13.4},\"clouds\":{\"all\":1},\"dt\":1521242100,\"sys\":{\"type\":1,\"id\":1969,\"message\":0.0052,\"country\":\"US\",\"sunrise\":1521198202,\"sunset\":1521241431},\"id\":420026193,\"name\":\"East Atlantic Beach\",\"cod\":200}", 
										  "{\"cod\":\"404\",\"message\":\"city not found\"}"};
    
	private final String[] zipcodes = {"71203", "11355", "00000"};
	
	/*
	 * Attempts to break the WeatherData object by passing it
	 * mock info I captured from the api. If an error is thrown
	 * we expect to see the city not found exception.
	 */
	@Test
	public void test() {
		for(int i = 0; i < 3; i++){
			WeatherData wd = null;
			try {
				wd = new Gson().fromJson(mockZipData[i], WeatherData.class);
				wd.setZipcode(zipcodes[i]);
				if(!wd.checkCodResult()){
					throw new Exception(wd.getErrorMessage());
				}
			} catch (Exception e) {
				assertEquals(e.getMessage(), ("city not found for: " + wd.getZipcode()));
			}
		}
	}
}
