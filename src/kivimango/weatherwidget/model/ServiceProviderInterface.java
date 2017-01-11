package kivimango.weatherwidget.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public interface ServiceProviderInterface {
	
	public Weather getWeatherData(String city) throws MalformedURLException, JsonIOException, JsonSyntaxException, IOException;
	public JsonObject doApiCall(URL urlString) throws JsonIOException, JsonSyntaxException, IOException;
	public Weather processDataFromProvider(JsonObject responseToProcess);
	public List<WeatherForecast> processForecastFromProvider(JsonObject forecastToProcess);
	public String getName();
	
}
