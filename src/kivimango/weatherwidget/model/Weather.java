package kivimango.weatherwidget.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Data storing class for storing the weather details from a JSON response
 * Values will be filed by the ServiceProvider class
 * 
 * @author Hicks
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public class Weather {
	
	private HashMap<String, ImageIcon> iconSet = new HashMap<>();

	private String countryCode = "";
	
	private String cityName = "";
	
	private String weatherType = "";
	
	private String weatherDescription = "";
	
	private String weatherIcon = "";
	
	private double temperature = 0.0;
	
	private List<WeatherForecast> forecast = new ArrayList<>();
	
	public Weather() {
		
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	
	public String getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon += weatherIcon + ".png";
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public List<WeatherForecast> getForecast() {
		return forecast;
	}

	public void setForecast(List<WeatherForecast> forecast) {
		this.forecast = forecast;
	}
	
	@Override
	public String toString() {
		return "Weather [iconSet=" + iconSet + ", countryCode=" + countryCode + ", cityName=" + cityName
				+ ", weatherType=" + weatherType + ", weatherDescription=" + weatherDescription + ", weatherIcon="
				+ weatherIcon + ", temperature=" + temperature + "]";
	}

}
