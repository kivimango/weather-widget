package kivimango.weatherwidget.model;

import kivimango.weatherwidget.model.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handling the configuration file.Checking, setting, loading, saving.
 * 
 * @author 		kivimango
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

public class SettingsLoader {
	
	private String settingsFileName = "weather-widget.properties";
	
	private final String pathToSettingsFile;
	
	private Settings settings = new Settings();
	
	private final String defaultCity = "Budapest";

	public SettingsLoader() {
		pathToSettingsFile = System.getProperty("user.home")+File.separator+settingsFileName;
		System.out.println(pathToSettingsFile);
	}
	
	public Settings getSettings() {
		if(checkSettingsFileExists(pathToSettingsFile))
		{
			settings = loadSettingsFromFile(pathToSettingsFile);
		}
		
		// Configuration file does not exists (on the first run of the program),
		// creating one with the default configuration values. 
		
		else {
			settings = loadDefaultSettings();
			saveSettingsFile(settings);
		}
		
		return settings;
	}
	
	/**
	 * Loading the configuration values form the configuration file into the Settings class.
	 * The configuration file has a JSON syntax
	 * @param path Path to the configuration file.
	 * @return
	 */
	
	protected Settings loadSettingsFromFile(String path)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String fileContent = "";
			String line = "";
			
			while ((line = br.readLine()) != null) {
				fileContent += line; 
        	}
			
			br.close();
			
			JsonParser parser = new JsonParser();
			JsonElement rootElement = parser.parse(fileContent);

			try {
				String jsonCity = rootElement.getAsJsonObject().get("city").getAsString();
				settings.setCity(jsonCity);
			}
			
			// invalid JSON syntax in the settings file, rewriting with the defaults values
			
			catch ( IllegalStateException e )
			{
				Settings defaultSettings = loadDefaultSettings();
				saveSettingsFile(defaultSettings);
				settings = defaultSettings;
			}
		}
		
		// Could not load file (no rights to access, HDD fault, etc)
		
		catch (IOException e) {
			System.out.println(e.getMessage());
			settings.setCity(defaultCity);
		}
		
		return settings;
	}

	/**
	 * Saving the settings values to a text file in JSON syntax
	 * @param path Path to save the configuration file
	 * @param settingsFile the instance of the Settings class with the actual values
	 */
	
	public void saveSettingsFile(Settings settingsFile)
	{
		JsonObject settingsToSave = new JsonObject();
		settingsToSave.addProperty("city", settingsFile.getCity());
		try (FileWriter file = new FileWriter(pathToSettingsFile)) {
			file.write(settingsToSave.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Setting the default configuration values. 
	 * @return Default settings
	 */
	
	protected Settings loadDefaultSettings()
	{
		Settings defaults = new Settings();
		defaults.setCity(defaultCity);
		
		return defaults;
	}
	
	/**
	 * Checking if the configuration exists in the predefined path of the file system.
	 * @param path Path to the configuration file.
	 * @return
	 */
	
	protected boolean checkSettingsFileExists(String path)
	{
		File f = new File(path);
		if(f.exists() && !f.isDirectory())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
