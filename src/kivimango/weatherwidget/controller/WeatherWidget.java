package kivimango.weatherwidget.controller;

import kivimango.weatherwidget.model.ServiceProvider;
import kivimango.weatherwidget.model.Settings;
import kivimango.weatherwidget.model.SettingsLoader;
import kivimango.weatherwidget.model.Weather;
import kivimango.weatherwidget.view.WidgetWindow;

import java.io.IOException;
import java.net.MalformedURLException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Desktop Weather Widget
 * 
 * Displays the current weather information on your desktop in a little, fancy window.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.controller
 */

public class WeatherWidget {

	public static void main(String[] args) {
		SettingsLoader settingsLoader = new SettingsLoader();
		Settings settings = settingsLoader.getSettings();
		ServiceProvider provider = new ServiceProvider(settings.getCity());
		Weather response = new Weather();
		
		try {
			WidgetWindow window = new WidgetWindow();
			window.statusPanel.setEnableAnimation(true);
			response = provider.getWeatherData();
			window.setWeatherData(response);
			window.statusPanel.setEnableAnimation(false);
			System.out.println("A hõmérséklet: " + response.getTemperature());
			
		} catch ( MalformedURLException e) {
			System.out.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid city name !)");
		} catch (JsonIOException e) {
			System.out.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Service not available !)");
		} catch ( JsonSyntaxException ee) {
			System.out.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid URL !)");
		} catch ( IOException eee) {
			System.out.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Unable to read stream !)");
		}
		
	}
}
