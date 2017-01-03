package kivimango.weatherwidget.model;

/**
 * Storing the settings loaded from the configuration file.Values set by SettingsLoader
 * 
 * @author		kivimango
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public class Settings {
	
	private String city;
	
	public Settings() {
		setCity("Budapest");
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

}
