/**
 * Desktop Weather Widget
 * 
 * Displays the current weather information on your desktop in a little, fancy window.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.model
 */

package kivimango.weatherwidget.model;

import kivimango.weatherwidget.model.Cache;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class WeatherWidget {

	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, IOException {
		
		double temperature = 0.0;
		
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Budapest,hu&APPID=b4fdc5e7a35e6a2c9e95b0b2c6a69600&units=metric");
			
			JsonParser parser = new JsonParser();
			JsonElement rootElement = parser.parse(new InputStreamReader(url.openStream()));
			
			JsonObject response = rootElement.getAsJsonObject();
			
			temperature = response.get("main").getAsJsonObject().get("temp").getAsDouble();
			
			Cache.put("temp", response);
			
			System.out.println("A hõmérséklet: " + temperature);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot retrieve information from the provider ! (Invalid url)");
		}
		
		
		
	}

}
