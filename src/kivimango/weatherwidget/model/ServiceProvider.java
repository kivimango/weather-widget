package kivimango.weatherwidget.model;

import java.net.MalformedURLException;
import java.net.URL;

public class ServiceProvider {
	
	private String name;
	
	private URL apiCallUrl;
	
	private String apiKey;
	
	private String queryString;

	public ServiceProvider() throws MalformedURLException {
		super();
		this.name = "Open Weather Map";
		this.apiCallUrl = new URL("api.openweathermap.org/data/2.5/weather?q={city name}");
		this.apiKey = "b4fdc5e7a35e6a2c9e95b0b2c6a69600";
		this.queryString = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getUrl() {
		return apiCallUrl;
	}

	public void setUrl(URL url) {
		this.apiCallUrl = url;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public void apiCall()
	{
	}

}
