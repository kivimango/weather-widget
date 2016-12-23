package kivimango.weatherwidget.model;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public interface ServiceProviderInterface {
	
	public Weather getWeatherData() throws JsonIOException, JsonSyntaxException, IOException;
	public JsonObject doApiCall() throws JsonIOException, JsonSyntaxException, IOException;
	public Weather processDataFromProvider(JsonObject responseToProcess);
	public String getName();

}
