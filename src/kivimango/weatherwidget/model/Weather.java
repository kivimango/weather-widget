package kivimango.weatherwidget.model;

/**
 * Data storing class for storing the weather details from a JSON response
 * Values will be filed by the ServiceProvider class
 * 
 * @author Hicks
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public final class Weather {

	final private String countryCode;
	
	final private String cityName;
	
	final private String weatherType;
	
	final private String weatherDescription;
	
	final private String weatherIcon;
	
	final private double temperature;

	public Weather(String countryCode, String cityName, String weatherType, String weatherDescription,
			String weatherIcon, double temperature) {
		super();
		this.countryCode = countryCode;
		this.cityName = cityName;
		this.weatherType = weatherType;
		this.weatherDescription = weatherDescription;
		this.weatherIcon = weatherIcon;
		this.temperature = temperature;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCityName() {
		return cityName;
	}

	public String getWeatherType() {
		return weatherType;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}
	
	public String getWeatherIcon() {
		return weatherIcon;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	@Override
	public String toString() {
		return "Weather [countryCode=" + countryCode + ", cityName=" + cityName
				+ ", weatherType=" + weatherType + ", weatherDescription=" + weatherDescription + ", weatherIcon="
				+ weatherIcon + ", temperature=" + temperature + "]";
	}

}
