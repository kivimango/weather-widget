package kivimango.weatherwidget.model;

import java.util.Date;

/**
 * Data storing class for the forecast details
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public class WeatherForecast {
	
	private Date time;
	
	private double temperature;

	private String icon;
	
	private String description;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
