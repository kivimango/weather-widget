package kivimango.weatherwidget.model;

import java.util.Date;

/**
 * Data storing class for the forecast details
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public final class WeatherForecast {
	
	final private Date time;
	
	final private double temperature;

	final private String icon;
	
	final private String description;

	public WeatherForecast(Date time, double temperature, String icon, String description) {
		super();
		this.time = time;
		this.temperature = temperature;
		this.icon = icon;
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public double getTemperature() {
		return temperature;
	}

	public String getIcon() {
		return icon;
	}

	public String getDescription() {
		return description;
	}

}
