package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import kivimango.weatherwidget.model.ServiceProvider;
import kivimango.weatherwidget.model.Settings;
import kivimango.weatherwidget.model.SettingsLoader;
import kivimango.weatherwidget.model.Weather;

/**
 * Implementation of the Widget's window which will display the weather details
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class WidgetWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Weather weatherData = new Weather();
	
	HashMap<String, String> backgroundList = new HashMap<>();
    
	JLabel backgroundPanel = new JLabel();
	
	UpperButtonPanel upperButtonPanel = new UpperButtonPanel();
	
	DetailPanel detailPanel = new DetailPanel();
	
	ForecastPanel forecastPanel;
	
	public StatusPanel statusPanel = new StatusPanel();
	
	ImageIcon icon = new ImageIcon();
	
	static SettingsPanel settingsPanel = new SettingsPanel();
	
	final String backgroundFolder = "/backgrounds/";
	
	static SettingsLoader settingsLoader = new SettingsLoader();
	static Settings settings = settingsLoader.getSettings();
	static ServiceProvider provider = new ServiceProvider(settings.getCity());
	static Weather response = new Weather();
	
	public static void main(String[] args) {
		try {
			WidgetWindow window = new WidgetWindow();
			window.statusPanel.setEnableAnimation(true);
			response = provider.getWeatherData();
			window.setWeatherData(response);
			window.statusPanel.setEnableAnimation(false);
			System.out.println("A hõmérséklet: " + response.getTemperature());
			
		} catch ( MalformedURLException e) {
			int result = JOptionPane.showConfirmDialog(settingsPanel, "Invalid URL! Cannot read stream from the provided adress!", new String("Error"), JOptionPane.DEFAULT_OPTION );
			handleUrlException(result);
		} catch (JsonIOException e) {
			int result = JOptionPane.showConfirmDialog(settingsPanel, "Service not available!", new String("Error"), JOptionPane.DEFAULT_OPTION );
			handleJsonException(result);
		} catch ( JsonSyntaxException ee) {
			int result = JOptionPane.showConfirmDialog(settingsPanel, "Invalid Url! Cannot read stream from the provided adress!", new String("Error"), JOptionPane.DEFAULT_OPTION ); 
			handleJsonSyntaxException(result);
		} catch ( IOException eee) {
			int result = JOptionPane.showConfirmDialog(settingsPanel, "Press OK to reset the the settings and restart the application!", 
					new String("Configuration file missing or corrupted!"), JOptionPane.DEFAULT_OPTION );
			handleIOException(result);
			}
	}

	public WidgetWindow() throws MalformedURLException
	{
		initWindow();
		fillBackGroundList();
		setTransparency(10);
		setUpBackground();
		setVisible(true);
	}
	
	/**
	 * Setting up the main JFrame's properties
	 */
	
	private void initWindow()
	{	
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 275;
		int startY = (screenSize.height - frameSize.height) - 650;
		
		add(backgroundPanel);
		
		getRootPane().setBorder(BorderFactory.createDashedBorder(getBackground(), 1, 1, 1, true));
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		setLayout(null);
		setMinimumSize(new Dimension(200,600));
		setLocation(startX, startY);
		setResizable(false);
		setTitle("Desktop Weather Widget");
		setUndecorated(true);
		
		pack();
	}
	
	/**
	 * Setting up the background component's properties and adding the child components
	 */
	
	private void setUpBackground()
	{
		backgroundPanel.setBounds(0,0, 200, 600);
		backgroundPanel.setLayout(null);
		backgroundPanel.add(upperButtonPanel);
		backgroundPanel.add(detailPanel);
		backgroundPanel.add(settingsPanel);
		
		backgroundPanel.add(statusPanel);
	}
	
	public void setWeatherData(Weather data) throws MalformedURLException
	{
		weatherData = data;
		
		// Need to truncate the double value to a simple integer (eg 12.35 to just 12)
		
		detailPanel.temperatureLabel.setText((int)weatherData.getTemperature() + "\u00b0C");
		try {
			detailPanel.weatherIcon.setIcon(new ImageIcon(new URL(weatherData.getWeatherIcon())));
		} catch (MalformedURLException e) {
			detailPanel.weatherIcon.setIcon(new ImageIcon());
		}
		
		detailPanel.weatherTypeLabel.setText(weatherData.getWeatherType());
		detailPanel.weatherTypeLabel.setToolTipText(weatherData.getWeatherDescription());
		
		upperButtonPanel.countryNameLabel.setText(weatherData.getCountryCode() + ", " + weatherData.getCityName());
		
		forecastPanel = new ForecastPanel(data.getForecast());
		
		setBackgroundByWeatherCondition(data.getWeatherType());
		
		backgroundPanel.add(forecastPanel);
	}
	
	/**
	 * Filling the hash map with the predefined background file's names and extensions
	 * @see https://openweathermap.org/weather-conditions
	 */
	
	private void fillBackGroundList()
	{
		backgroundList.put("additional", "additional.jpg");
		backgroundList.put("atmosphere", "atmosphere.jpg");
		backgroundList.put("clear", "clear.jpg");
		backgroundList.put("clouds", "clouds.jpg");
		backgroundList.put("drizzle", "rain.jpg");
		backgroundList.put("extreme", "extreme.jpg");
		backgroundList.put("fog", "fog.jpg");
		backgroundList.put("haze", "haze.jpg");
		backgroundList.put("mist", "mist.jpg");
		backgroundList.put("rain", "rain.jpg");
		backgroundList.put("thunderstorm", "thunderstorm.jpg");
		backgroundList.put("snow", "snow.jpg");
	}
	
	/**
	 * Setting the main JFrame's background based on the weather condition string e.g. "Sunny" or "Clouds"
	 * TO-DO: If the file doesn't exists, the script will be fall back to the default (sunny) background
	 * @param condition The Weather condition from the JSON response
	 */
	
	private void setBackgroundByWeatherCondition(String condition)
	{
		condition = condition.toLowerCase();
		
		if (backgroundList.containsKey(condition))
		{
			System.out.println(backgroundList.get(condition));
			try {
				BufferedImage bg = null;
				bg = ImageIO.read(getClass().getResource(backgroundFolder + backgroundList.get(condition)));
				backgroundPanel.setIcon(new ImageIcon(bg));
			} catch (IOException | IllegalArgumentException e) {
				// Background picture not found, fall back to the default black colored background
				this.setBackground(Color.black);
			}
		}
	}
	
	/**
	 * Making the window transparent
	 * @author arc 
	 * @param opacity The value of Transparency (1 to 10, otherwise the default value is 5).
	 * Pass lesser values to make the window more transparent.
	 * @see http://stackoverflow.com/questions/22027247/java-setting-background-from-jar-and-making-it-transparent
	 */
	
	private void setTransparency(float opacity)
	{
		if( (opacity < 1) || (opacity > 10))
		{
			opacity = 5;
		}
		
		float fOpacity = (float) opacity / 10;
		String sOpacity = Float.toString(fOpacity) + "f";
		setOpacity(Float.parseFloat(sOpacity));
	}
	
	private static void handleJsonException(int result) {
		System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Service not available!)");
		if(result == 0)
		{
			System.exit(0);
		}
	}
	
	private static void handleJsonSyntaxException(int result) {
		System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid URL!)");
		if(result == 0)
		{
			System.exit(0);
		}
	}
	
	private static void handleUrlException(int result)
	{
		System.err.println("Cannot retrieve information from the provider: "+provider.getName()+" ! (Invalid city name!)");
		if(result == 0)
		{
			// creating a new configuration file with the default values of Settings class
			settingsLoader.saveSettingsFile(new Settings());
			System.exit(0);
		}
	}
	
	private static void handleIOException(int result)
	{
		System.err.println("I/O Exception occured: Configuration file missing or corrupted!");
		System.err.println("Attempting to reset the settings file...");
		if(result == 0)
		{
			// creating a new configuration file with the default values of Settings class
			settingsLoader.saveSettingsFile(new Settings());
			System.exit(0);
		}
	}

	public void reload() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException {
			statusPanel.setEnableAnimation(true);
			response = provider.getWeatherData();
			setWeatherData(response);
			statusPanel.setEnableAnimation(false);
	}
}