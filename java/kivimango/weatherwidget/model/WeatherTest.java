package kivimango.weatherwidget.model;

import org.junit.Assert;
import org.junit.Test;

public class WeatherTest {

	@Test
	public void weatherCreationTest() {
		// Given
		String testCountryCode = "HU";
		String testCityName = "Budapest";
		String testWeatherType = "Clouds";
		String testWeatherDescription = "Scattered clouds";
		String testIcon = "01d";
		double testTemperature = 18.0;
		
		// When
		Weather weather = new Weather(testCountryCode, testCityName, testWeatherType, testWeatherDescription, testIcon, testTemperature);
		
		// Then
		Assert.assertEquals(testCountryCode, weather.getCountryCode());
		Assert.assertEquals(testCityName, weather.getCityName());
		Assert.assertEquals(testWeatherType, weather.getWeatherType());
		Assert.assertEquals(testWeatherDescription, weather.getWeatherDescription());
		Assert.assertEquals(testIcon, weather.getWeatherIcon());
		Assert.assertEquals(testTemperature, weather.getTemperature(), 0.0);
		Assert.assertTrue(weather instanceof Weather);
	}
}
