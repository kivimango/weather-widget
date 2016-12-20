package kivimango.weatherwidget.model;

public class ApiResponse {

	private String countryCode = "";
	
	private String cityName = "";
	
	private String weatherType = "";
	
	private String weatherDescription = "";
	
	private double temperature = 0.0;
	
	private double temperatureMin = 0.0;
	
	private double temperatureMax = 0.0;
	
	private int pressure = 0;
	
	private int humidity = 0;
	
	private int visibility = 0;
	
	private double windSpeed = 0.0;
	
	private int windDegree= 0;
	
	private int cloudiness = 0;
	
	private long sunries = 0;
	
	private long sunset = 0;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public double getTemperatureMin() {
		return temperatureMin;
	}
	
	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	
	public double getTemperatureMax() {
		return temperatureMax;
	}
	
	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public int getWindDegree() {
		return windDegree;
	}

	public void setWindDegree(int windDegree) {
		this.windDegree = windDegree;
	}

	public int getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(int cloudiness) {
		this.cloudiness = cloudiness;
	}

	public long getSunries() {
		return sunries;
	}

	public void setSunries(long sunries) {
		this.sunries = sunries;
	}

	public long getSunset() {
		return sunset;
	}

	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
}
