package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import kivimango.weatherwidget.model.Weather;
import kivimango.weatherwidget.model.WeatherForecast;
import kivimango.weatherwidget.view.CloseButton;

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
	
	private HashMap<String, String> backgroundList = new HashMap<>();
    
	JLabel backgroundPanel = new JLabel();
	
	JPanel upperButtonPanel = new JPanel();
	
	JPanel detailPanel = new JPanel();
	
	JPanel forecastPanel = new JPanel();
	
	ImageIcon icon = new ImageIcon();
	
	CloseButton closeButton = new CloseButton();
	SettingsButton settingsButton = new SettingsButton();
	
	JLabel temperatureLabel = new JLabel();
	JLabel weatherIcon		= new JLabel();
	JLabel weatherTypeLabel = new JLabel();
	JLabel countryCodeLabel = new JLabel();
	JLabel countryNameLabel = new JLabel();
	
	Calendar calendar = Calendar.getInstance();
	
	static SettingsPanel settingsPanel = new SettingsPanel();
	
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
		setUpForecastPanel(weatherData.getForecast());
		
		setVisible(true);
	}
	
	private void initWindow()
	{	
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 275;
		int startY = (screenSize.height - frameSize.height) - 650;
		
		add(backgroundPanel);
		
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		setLayout(null);
		setMinimumSize(new Dimension(200,600));
		setLocation(startX, startY);
		setResizable(false);
		setTitle("Desktop Weather Widget");
		setUndecorated(true);
		
		pack();
	}
	
	private void setUpBackground()
	{
		backgroundPanel.setBounds(0,0, 200, 600);
		backgroundPanel.setLayout(null);
	}
	
	private void setUpUpperButtonPanel()
	{
		upperButtonPanel.add(settingsButton);
		upperButtonPanel.add(closeButton);
		upperButtonPanel.setBounds(0, 0, 200, 50);
		upperButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperButtonPanel.setOpaque(false);
		
		backgroundPanel.add(upperButtonPanel);
	}
	
	private void setUpDetailPanel()
	{
		detailPanel.setBounds(0, 50, 200, 200);
		detailPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		detailPanel.setOpaque(false);
		
		temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		temperatureLabel.setForeground(Color.WHITE);
		temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 44));
		temperatureLabel.setMinimumSize(new Dimension(200, 50));
		temperatureLabel.setMaximumSize(new Dimension(200, 50));
		temperatureLabel.setOpaque(false);
		temperatureLabel.setPreferredSize(new Dimension(200, 50));
		
		detailPanel.add(temperatureLabel);
		
		weatherIcon.setMinimumSize(new Dimension(50, 100));
		weatherIcon.setMaximumSize(new Dimension(50, 100));
		weatherIcon.setOpaque(false);
		weatherIcon.setPreferredSize(new Dimension(50, 100));
		
		detailPanel.add(weatherIcon);
		
		weatherTypeLabel.setForeground(Color.WHITE);
		weatherTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		weatherTypeLabel.setMinimumSize(new Dimension(130, 100));
		weatherTypeLabel.setMaximumSize(new Dimension(130, 100));
		weatherTypeLabel.setOpaque(false);
		weatherTypeLabel.setPreferredSize(new Dimension(130, 100));
		
		detailPanel.add(weatherTypeLabel);
		
		countryCodeLabel.setForeground(Color.WHITE);
		countryCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		countryCodeLabel.setOpaque(false);
		
		detailPanel.add(countryCodeLabel);
		
		countryNameLabel.setForeground(Color.WHITE);
		countryNameLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		countryNameLabel.setOpaque(false);
		
		detailPanel.add(countryNameLabel);
		
		backgroundPanel.add(detailPanel);
		backgroundPanel.add(settingsPanel);
	}
	
	private void setUpForecastPanel(List<WeatherForecast> forecastData) throws MalformedURLException
	{	
		JPanel columnA = makeGrid(forecastData.get(0));
		JPanel columnB = makeGrid(forecastData.get(1));
		JPanel columnC = makeGrid(forecastData.get(2));
		
		forecastPanel.setBounds(0, 250, 200, 200);
		forecastPanel.setLayout(new GridLayout());
		forecastPanel.setOpaque(false);
		
		forecastPanel.add(columnA);
		forecastPanel.add(columnB);
		forecastPanel.add(columnC);
		
		backgroundPanel.add(forecastPanel);
	}
	
	private JPanel makeGrid( WeatherForecast dataToSet) throws MalformedURLException
	{
		JPanel grid = new JPanel();
		
		JLabel timeLabel = new JLabel();
		JLabel temperatureLabel = new JLabel();
		JLabel iconLabel = new JLabel();
		JTextArea descLabel = new JTextArea();
		
		calendar.setTime(dataToSet.getTime());
		
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.white);
		timeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		timeLabel.setPreferredSize(new Dimension(65, 50));
		timeLabel.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
		+ Integer.toString(calendar.get(Calendar.MINUTE)) + Integer.toString(calendar.get(Calendar.MINUTE)));

		temperatureLabel.setForeground(Color.white);
		temperatureLabel.setText(Double.toString(dataToSet.getTemperature()) + "\u00b0C");
		
		iconLabel.setIcon(new ImageIcon(new URL(dataToSet.getIcon())));
		
		descLabel.setEditable(false);
		descLabel.setLineWrap(true);
		descLabel.setForeground(Color.white);
		descLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		descLabel.setOpaque(false);
		descLabel.setPreferredSize(new Dimension(65, 100));
		descLabel.setText(dataToSet.getDescription());
		descLabel.setWrapStyleWord(true);
		
		grid.add(timeLabel);
		grid.add(temperatureLabel);
		grid.add(iconLabel);
		grid.add(descLabel);
		
		grid.setLayout(new FlowLayout(FlowLayout.CENTER));
		grid.setMinimumSize(new Dimension(65, 150));
		grid.setMaximumSize(new Dimension(65, 150));
		grid.setOpaque(false);
		
		return grid;
	}
	
	private void setWeatherData()
	{
		temperatureLabel.setText(weatherData.getTemperature() + "\u00b0C");
		try {
			weatherIcon.setIcon(new ImageIcon(new URL(weatherData.getWeatherIcon())));
		} catch (MalformedURLException e) {
			weatherIcon.setIcon(new ImageIcon());
		}
		
		weatherTypeLabel.setText(weatherData.getWeatherType());
		weatherTypeLabel.setToolTipText(weatherData.getWeatherDescription());
		
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
}
