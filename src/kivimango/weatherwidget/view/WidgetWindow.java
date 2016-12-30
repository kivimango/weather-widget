package kivimango.weatherwidget.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kivimango.weatherwidget.model.Weather;
import kivimango.weatherwidget.view.CloseButton;

/**
 * Implementation of the Widget's window which will display the weather details
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class WidgetWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Weather weatherData = new Weather();
	
	private HashMap<String, String> backgroundList = new HashMap<>();
    
	JLabel backgroundPanel = new JLabel();
	
	JPanel upperButtonPanel = new JPanel();
	
	JPanel detailPanel = new JPanel();
	
	ImageIcon icon = new ImageIcon();
	
	CloseButton closeButton = new CloseButton();
	SettingsButton settingsButton = new SettingsButton();
	
	JLabel temperatureLabel = new JLabel();
	JLabel weatherIcon		= new JLabel();
	JLabel weatherTypeLabel = new JLabel();
	JLabel countryCodeLabel = new JLabel();
	JLabel countryNameLabel = new JLabel();
	
	SettingsPanel settingsPanel = new SettingsPanel();
	
	final String backgroundFolder = "/backgrounds/";
	
	public WidgetWindow(Weather response) throws MalformedURLException
	{
		initWindow();
		
		weatherData = response;
		fillBackGroundList();
		setWeatherData();
		setBackgroundByWeatherCondition(weatherData.getWeatherType());
		setTransparency(10);
		
		setUpBackground();
		setUpUpperButtonPanel();
		setUpDetailPanel();
	
		setVisible(true);
	}
	
	private void initWindow()
	{	
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 275;
		int startY = (screenSize.height - frameSize.height) - 650;
		
		setTitle("Desktop Weather Widget");
		setLayout(new BorderLayout(5, 5));	
		add(backgroundPanel);
		setMinimumSize(new Dimension(200,600));
		setLocation(startX, startY);
		setResizable(false);
		setUndecorated(true);
		
		add(settingsPanel);
		
		pack();
	}
	
	private void setUpBackground()
	{
		backgroundPanel.setLayout(new BorderLayout());
	}
	
	private void setUpUpperButtonPanel()
	{	
		upperButtonPanel.add(closeButton, BorderLayout.EAST);
		upperButtonPanel.add(settingsButton, BorderLayout.WEST);
		upperButtonPanel.setOpaque(false);
		backgroundPanel.add(upperButtonPanel, BorderLayout.PAGE_START);
	}
	
	private void setUpDetailPanel()
	{
		detailPanel.setOpaque(false);
		
		temperatureLabel.setOpaque(false);
		temperatureLabel.setForeground(Color.WHITE);
		temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 44));
		temperatureLabel.setName("temperatureLabel");
		
		detailPanel.add(temperatureLabel, BorderLayout.CENTER);
		
		detailPanel.add(weatherIcon);
		
		weatherTypeLabel.setOpaque(false);
		weatherTypeLabel.setForeground(Color.WHITE);
		weatherTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		weatherTypeLabel.setName("weatherTypeLabel");
		
		detailPanel.add(weatherTypeLabel);
		
		countryCodeLabel.setOpaque(false);
		countryCodeLabel.setForeground(Color.WHITE);
		countryCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		countryCodeLabel.setName("countryCodeLabel");
		
		detailPanel.add(countryCodeLabel, BorderLayout.LINE_START);
		
		countryNameLabel.setOpaque(false);
		countryNameLabel.setForeground(Color.WHITE);
		countryNameLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		countryNameLabel.setName("countryNameLabel");
		
		detailPanel.add(countryNameLabel, BorderLayout.LINE_END);
		
		backgroundPanel.add(detailPanel);
	}
	
	private void setWeatherData()
	{
		temperatureLabel.setText(weatherData.getTemperature() + "\u00b0C");
		try {
			weatherIcon.setIcon(new ImageIcon(new URL(weatherData.getWeatherIcon())));
		} catch (MalformedURLException e) {
			weatherIcon.setIcon(new ImageIcon());
		}
		
		weatherTypeLabel.setText(weatherData.getWeatherType() + "   ");
		
		countryCodeLabel.setText(weatherData.getCountryCode() + ", ");
		countryNameLabel.setText(weatherData.getCityName());
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
		backgroundList.put("drizzle", "drizzle.jpg");
		backgroundList.put("extreme", "extreme.jpg");
		backgroundList.put("mist", "fog.jpg");
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
}
