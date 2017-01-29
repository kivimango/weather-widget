package kivimango.weatherwidget.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Class for the free GeoIP HTTP API.
 * Just send a GET request to the API URL, 
 * and the service will return in your town's name and some details in JSON format based on your IP address.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @see 		https://freegeoip.net/
 * @package 	kivimango.weatherwidget.model
 */

public class GeoIpLocator {
	
	// No using https protocol, because it throws PKIX path building failed exception.
	
	final String API_URL = "http://freegeoip.net/json/";
	
	String city;

	public GeoIpLocator() throws JsonIOException, JsonSyntaxException {
		URL url;
		try {
			url = new URL(API_URL);
			JsonParser parser = new JsonParser();
			JsonElement rootElement = parser.parse(new InputStreamReader(url.openStream()));
			JsonObject responseJson = rootElement.getAsJsonObject();
			city = responseJson.get("city").getAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	public String getCityName() {
		return city;
	}
}
