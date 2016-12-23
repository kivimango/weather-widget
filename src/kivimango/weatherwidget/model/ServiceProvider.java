package kivimango.weatherwidget.model;

import kivimango.weatherwidget.model.Weather;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ServiceProvider implements ServiceProviderInterface {
	
	private String name;
	private String apiCallUrl;
	private String city;
	private String apiKey;
	private URL queryString;
	private Weather response = new Weather();

	public ServiceProvider() throws MalformedURLException {
		super();
		name = "Open Weather Map";
		apiKey = "b4fdc5e7a35e6a2c9e95b0b2c6a69600";
		apiCallUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		city = "Budapest,hu";
		queryString = new URL(apiCallUrl + city + "&units=metric&APPID=" + apiKey);
	}
	
	public Weather getWeatherData() throws JsonIOException, JsonSyntaxException, IOException
	{
		JsonObject responseFromProvider = this.doApiCall();
		response = this.processDataFromProvider(responseFromProvider);
		
		return response;
	}
	
	public JsonObject doApiCall() throws JsonIOException, JsonSyntaxException, IOException
	{
		JsonParser parser = new JsonParser();
		JsonElement rootElement = parser.parse(new InputStreamReader(queryString.openStream()));
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
		
		response.setWeatherType(tempWeatherType);
		response.setWeatherDescription(tempWeatherDescription);
		response.setTemperature(temperature);
		response.setCountryCode(tempCountryCode);
		response.setCityName(tempCityName);		
		response.setWeatherIcon(iconn);
		
		return response;
	}
	
	public String getName() {
		return name;
	}

}
