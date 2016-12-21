package kivimango.weatherwidget.model;

import kivimango.weatherwidget.model.ApiResponse;

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
	private String apiCallUrlParams;
	private String apiKey;
	private URL queryString;
	private ApiResponse response = new ApiResponse();

	public ServiceProvider() throws MalformedURLException {
		super();
		this.name = "Open Weather Map";
		this.apiKey = "b4fdc5e7a35e6a2c9e95b0b2c6a69600";
		this.apiCallUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		this.apiCallUrlParams = "Budapest,hu&units=metric&APPID=" + this.apiKey;
		this.queryString = new URL(this.apiCallUrl + this.apiCallUrlParams);
	}
	
	public ApiResponse getWeatherData() throws JsonIOException, JsonSyntaxException, IOException
	{
		JsonObject responseFromProvider = this.doApiCall();
		response = this.processDataFromProvider(responseFromProvider);
		
		return response;
	}
	
	private JsonObject doApiCall() throws JsonIOException, JsonSyntaxException, IOException
	{
		JsonParser parser = new JsonParser();
		JsonElement rootElement = parser.parse(new InputStreamReader(queryString.openStream()));
		JsonObject responseJson = rootElement.getAsJsonObject();
		
		return responseJson;
	}
	
	private ApiResponse processDataFromProvider(JsonObject responseToProcess)
	{
		JsonArray tempWeatherInfo = responseToProcess.get("weather").getAsJsonArray();
		String tempWeatherType = tempWeatherInfo.get(0).getAsJsonObject().get("main").getAsString();
		String tempWeatherDescription =  tempWeatherInfo.get(0).getAsJsonObject().get("description").getAsString();
		double temperature =  responseToProcess.get("main").getAsJsonObject().get("temp").getAsDouble();
		String tempCountryCode = responseToProcess.get("sys").getAsJsonObject().get("country").getAsString();
		String tempCityName = responseToProcess.get("name").getAsString();
		//int tempPressure = responseToProcess.get("main").getAsJsonObject().get("pressure").getAsInt();
		//int tempHumidity = responseToProcess.get("main").getAsJsonObject().get("humidity").getAsInt();
		//int tempMin =  responseToProcess.get("main").getAsJsonObject().get("temp_min").getAsInt();
		//int tempMax =  responseToProcess.get("main").getAsJsonObject().get("temp_max").getAsInt();
		//int tempVisibility = responseToProcess.get("main").getAsJsonObject().get("visibility").getAsInt();
		//double tempWindSpeed =  responseToProcess.get("main").getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble();
		//int tempWindDegree =  responseToProcess.get("main").getAsJsonObject().get("wind").getAsJsonObject().get("deg").getAsInt();
		
		//int tempCloudiness =  responseToProcess.get("main").getAsJsonObject().get("clouds").getAsJsonObject().get("all").getAsInt();
		
		response.setWeatherType(tempWeatherType);
		response.setWeatherDescription(tempWeatherDescription);
		response.setTemperature(temperature);
		//response.setPressure(tempPressure);
		//response.setHumidity(tempHumidity);
		response.setCountryCode(tempCountryCode);
		response.setCityName(tempCityName);		
		
		return response;
	}
	
	public String getName() {
		return name;
	}

}
