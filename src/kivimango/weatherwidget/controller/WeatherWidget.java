package kivimango.weatherwidget.controller;

import kivimango.weatherwidget.model.ServiceProvider;
import kivimango.weatherwidget.model.Settings;
import kivimango.weatherwidget.model.SettingsLoader;
import kivimango.weatherwidget.model.Weather;
import kivimango.weatherwidget.model.WeatherForecast;
import kivimango.weatherwidget.view.WidgetWindow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Desktop Weather Widget
 * 
 * Displays the current weather information on your desktop in a little, fancy window.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.controller
 */

public class WeatherWidget {

		static SettingsLoader settingsLoader = new SettingsLoader();
		static Settings settings = settingsLoader.getSettings();
		static ServiceProvider provider = new ServiceProvider();
		static WidgetWindow window;
		
		/**
		 * Storing the controller class in itself to access the reload method from the GUI's child component
		 * SettingsPanel.The SettingsPanel can request update weather information when user changing location in settings.
		 */
		
		public static WeatherWidget controller;
		
		public WeatherWidget() {
			controller = this;
		}
		
		public static void main(String[] args) {
			try {
				window = new WidgetWindow();
				window.statusPanel.setEnableAnimation(true);
				Weather response = provider.getWeatherData(settings.getCity());
				List<WeatherForecast> responseForecast = provider.getForecastData(settings.getCity());
				window.setWeatherData(response);
				window.setForecastData(responseForecast);
				window.statusPanel.setEnableAnimation(false);
			} catch ( MalformedURLException e) {
				int result = JOptionPane.showConfirmDialog(WidgetWindow.settingsPanel, "Invalid URL! Cannot read stream from the provided adress!", new String("Error"), JOptionPane.DEFAULT_OPTION );
				handleUrlException(result);
			} catch (JsonIOException e) {
				int result = JOptionPane.showConfirmDialog(WidgetWindow.settingsPanel, "Service not available!", new String("Error"), JOptionPane.DEFAULT_OPTION );
				handleJsonException(result);
			} catch ( JsonSyntaxException ee) {
				int result = JOptionPane.showConfirmDialog(WidgetWindow.settingsPanel, "Invalid Url! Cannot read stream from the provided adress!", new String("Error"), JOptionPane.DEFAULT_OPTION ); 
				handleJsonSyntaxException(result);
			} catch ( IOException eee) {
				int result = JOptionPane.showConfirmDialog(WidgetWindow.settingsPanel, "Press OK to reset the the settings and restart the application!", 
						new String("Configuration file missing or corrupted!"), JOptionPane.DEFAULT_OPTION );
				handleIOException(result);
				}
		}

		private static void handleJsonException(int result) {
			System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Service not available!)");
			if(result == 0)
			{
				System.exit(0);
			}
		}
		
		private static void handleJsonSyntaxException(int result) {
			System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid URL!)");
			if(result == 0)
			{
				System.exit(0);
			}
		}
		
		private static void handleUrlException(int result)
		{
			System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid city name!)");
			if(result == 0)
			{
				// creating a new configuration file with the default values of Settings class
				settingsLoader.saveSettingsFile(new Settings());
				System.exit(0);
			}
		}
		
		private static void handleIOException(int result)
		{
			System.err.println("I/O Exception occured: Configuration file missing or corrupted!");
			System.err.println("Attempting to reset the settings file...");
			if(result == 0)
			{
				// creating a new configuration file with the default values of Settings class
				settingsLoader.saveSettingsFile(new Settings());
				System.exit(0);
			}
		}

		/**
		 * Requesting new weather information from the provider based on a new location set by the user.
		 * This method will be called by the SettingsPanel's Apply button GUI component.
		 * @throws JsonIOException
		 * @throws JsonSyntaxException
		 * @throws MalformedURLException
		 * @throws IOException
		 */
		
		public static void reload() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException {
				window.statusPanel.setEnableAnimation(true);
				settings = settingsLoader.getSettings();
				Weather response = provider.getWeatherData(settings.getCity());
				List<WeatherForecast> responseForecast = provider.getForecastData(settings.getCity());
				window.setWeatherData(response);
				window.setForecastData(responseForecast);
				window.revalidate();
				window.repaint();
				window.statusPanel.setEnableAnimation(false);
		}
}