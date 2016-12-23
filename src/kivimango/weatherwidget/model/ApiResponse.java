package kivimango.weatherwidget.model;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class ApiResponse {
	
	private HashMap<String, ImageIcon> iconSet = new HashMap<>();

	private String countryCode = "";
	
	private String cityName = "";
	
	private String weatherType = "";
	
	private String weatherDescription = "";
	
	private String weatherIcon = "http://openweathermap.org/img/w/";
	
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

	public ApiResponse() {
		iconSet.put("cloudy", new ImageIcon("D:\\cloudy.png"));
		iconSet.put("drizzle", new ImageIcon("D:\\drizzle"));
		iconSet.put("haze", new ImageIcon("D:\\drizzle"));
		iconSet.put("mostly_cloudy", new ImageIcon("D:\\mostly_cloudy.png"));
		iconSet.put("slight_drizzle", new ImageIcon("D:\\slight_drizzle"));
		iconSet.put("snow", new ImageIcon("D:\\snow.png"));
		iconSet.put("sunny", new ImageIcon("D:\\sunny.png"));
		iconSet.put("thunderstorms", new ImageIcon("D:\\thunderstorms.png"));
	}

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

	public String getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon += weatherIcon + ".png";
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
	
	@Override
	public String toString() {
		return "ApiResponse [countryCode=" + countryCode + ", cityName=" + cityName + ", weatherType=" + weatherType
				+ ", weatherDescription=" + weatherDescription + ", temperature=" + temperature + ", temperatureMin="
				+ temperatureMin + ", temperatureMax=" + temperatureMax + ", pressure=" + pressure + ", humidity="
				+ humidity + ", visibility=" + visibility + ", windSpeed=" + windSpeed + ", windDegree=" + windDegree
				+ ", cloudiness=" + cloudiness + ", sunries=" + sunries + ", sunset=" + sunset + "]";
	}
}
