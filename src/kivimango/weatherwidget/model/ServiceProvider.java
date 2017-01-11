package kivimango.weatherwidget.model;

import kivimango.weatherwidget.model.Weather;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * This class is responsible for querying the Open Weather Map's API to 
 * retrieve the weather data (in JSON syntax) based on the passed parameters
 *  (location unit metrics, return format, in this case I'm using JSON format).
 *  
 *  Because of the JSON return format, i added the Google GSON library as a dependency to able to
 *  handle the JSON strings.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class ServiceProvider implements ServiceProviderInterface {
	
	private String name;
	private String apiCallUrl;
	private String forecastApiUrl;
	private String city;
	private String apiKey;
	private URL queryString;
	private URL forecastQueryString;
	private String iconUrl = "http://openweathermap.org/img/w/";

	public ServiceProvider() {
		super();
		name = "Open Weather Map";
		apiKey = "b4fdc5e7a35e6a2c9e95b0b2c6a69600";
		apiCallUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		forecastApiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";
	}
	
	public Weather getWeatherData(String cityBysettings) throws MalformedURLException, JsonIOException, JsonSyntaxException, IOException
	{
		city = cityBysettings;
		
		queryString = new URL(apiCallUrl + city + "&units=metric&APPID=" + apiKey);
		
		JsonObject responseFromProvider = doApiCall(queryString);
		return processDataFromProvider(responseFromProvider);
	}
	
	public List<WeatherForecast> getForecastData(String cityBySettings) throws JsonIOException, JsonSyntaxException, IOException
	{
		forecastQueryString = new URL(forecastApiUrl + cityBySettings + "&units=metric&APPID=" + apiKey);
		JsonObject forecastJSON = doApiCall(forecastQueryString);
		processForecastFromProvider(forecastJSON);
		
		return processForecastFromProvider(forecastJSON);
	}
	
	public JsonObject doApiCall(URL urlString) throws JsonIOException, JsonSyntaxException, IOException
	{
		JsonParser parser = new JsonParser();
		JsonElement rootElement = parser.parse(new InputStreamReader(urlString.openStream()));
		JsonObject responseJson = rootElement.getAsJsonObject();
		
		return responseJson;
	}
	
	public Weather processDataFromProvider(JsonObject responseToProcess)
	{
		JsonArray tempWeatherInfo = responseToProcess.get("weather").getAsJsonArray();
		String tempWeatherType = tempWeatherInfo.get(0).getAsJsonObject().get("main").getAsString();
		String tempWeatherDescription =  tempWeatherInfo.get(0).getAsJsonObject().get("description").getAsString();
		double temperature =  responseToProcess.get("main").getAsJsonObject().get("temp").getAsDouble();
		String tempCountryCode = responseToProcess.get("sys").getAsJsonObject().get("country").getAsString();
		String tempCityName = responseToProcess.get("name").getAsString();
		String iconn = tempWeatherInfo.get(0).getAsJsonObject().get("icon").getAsString();
		
		Weather response = new Weather(tempCountryCode, tempCityName, tempWeatherType, tempWeatherDescription,
				iconUrl + iconn, temperature);
		
		return response;
	}
	
	public List<WeatherForecast> processForecastFromProvider(JsonObject forecastToProcess)
	{
		JsonObject rootObject = forecastToProcess;
		JsonArray forecastArray = rootObject.get("list").getAsJsonArray();
		
		List<WeatherForecast> result = new ArrayList<>();
		
		for(int i = 0; i < forecastArray.size(); i++)
		{
			JsonObject forecastelement = forecastArray.get(i).getAsJsonObject();
			Date date = new Date(forecastelement.get("dt").getAsLong() * 1000);
			double temperature = forecastelement.get("main").getAsJsonObject().get("temp").getAsDouble();
			
			JsonObject details = forecastelement.get("weather").getAsJsonArray().get(0).getAsJsonObject();
			String icon = iconUrl + details.get("icon").getAsString() + ".png";
			String desc = details.get("description").getAsString();
			
			/*
			System.out.println("Hõmérséklet: " + temperature);
			System.out.println("Idõbélyeg: " + date);
			System.out.println("Ikon: " + icon);
			System.out.println("Leírás: " + desc);
			*/
			
			WeatherForecast buffer = new WeatherForecast(date, temperature, icon, desc);
			result.add(buffer);
		}
		
		return result;
	}
	
	public String getName() {
		return name;
	}

}
