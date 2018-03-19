package com.ws;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestMockCache {
	
	private final String[] zipsToTest = {"http://localhost:8080/api/wind/71203",
										 "http://localhost:8080/api/wind/98101",
										 "http://localhost:8080/api/wind/71203",
										 "http://localhost:8080/api/wind/71200",
										 "http://localhost:8080/api/wind/98101"};

	/*
	 * I'm pretty new to JUNIT testing, so i'm very sorry this
	 * is the only way i could really wrap my head
	 * around this part. I created a new instance of the overall
	 * application and then test to see if beans are created or
	 * cached on the fly. 
	 * I almost put in a sleep logic to show that a
	 * object fell out of the cache after so long, but
	 * decided it was too much.
	 */
	@Test
	public void test() {
		try {
		    final Class<WeatherServiceApplication> mainSpringRunner = WeatherServiceApplication.class;
		    final Method method = mainSpringRunner.getMethod("main", String[].class);

		    final Object[] args = new Object[1];
		    args[0] = new String[]{};
		    method.invoke(null, args);
		} catch (final Exception e) {
		    e.printStackTrace();
		}
		
		try {
			for(int i = 0; i < 5; i++){
				URL obj = new URL(zipsToTest[i]);
				System.out.println(IOUtils.toString(obj.openStream()));
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
