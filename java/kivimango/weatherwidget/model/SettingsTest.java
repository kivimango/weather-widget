package kivimango.weatherwidget.model;

import org.junit.Assert;
import org.junit.Test;

public class SettingsTest {
	
	@Test
	public void settingsCreationTest() {
		// Given
		String testCityName = "Tokyo";
		
		// When
		Settings defaultSettings = new Settings();
		Settings testSettings = new Settings();
		testSettings.setCity(testCityName);
		
		// Then
		// Budapest is the default city on creating the Settings instance
		Assert.assertEquals("Budapest", defaultSettings.getCity());
		Assert.assertEquals(testCityName, testSettings.getCity());
		Assert.assertTrue(defaultSettings instanceof Settings);
		Assert.assertTrue(testSettings instanceof Settings);
	}
	
}