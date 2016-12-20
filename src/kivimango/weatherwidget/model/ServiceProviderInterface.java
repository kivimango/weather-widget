package kivimango.weatherwidget.model;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface ServiceProviderInterface {
	
	public ApiResponse getWeatherData() throws JsonIOException, JsonSyntaxException, IOException;
	public String getName();

}
