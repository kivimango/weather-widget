package kivimango.weatherwidget.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class WeatherForecastTest {
	
	@Test
	public void weatherForecastCreationTest() {
		// Given
		Date testDate = new Date();
		double testTemperature = 23.6;
		String testIcon = "01n";
		String testDescription = "Clear sky";
		
		// When
		WeatherForecast forecast = new WeatherForecast(testDate, testTemperature, testIcon, testDescription);
		
		// Then
		Assert.assertEquals(testDate, forecast.getTime());
		Assert.assertEquals(testTemperature, forecast.getTemperature(), 0.0);
		Assert.assertEquals(testIcon, forecast.getIcon());
		Assert.assertEquals(testDescription, forecast.getDescription());
		Assert.assertTrue(forecast instanceof WeatherForecast);
	}

}
