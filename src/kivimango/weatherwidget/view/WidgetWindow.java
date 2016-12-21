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

package kivimango.weatherwidget.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import kivimango.weatherwidget.model.ApiResponse;

public class WidgetWindow {
	
	ApiResponse weatherData = new ApiResponse(); 
	
	JLabel temperatureLabel = new JLabel();
	JLabel weatherTypeLabel = new JLabel();
	JLabel countryCodeLabel = new JLabel();
	JLabel countryNameLabel = new JLabel();
	JFrame frame = initWindow();
	
	// TO-DO: there is a bug on setting the window's background color...fix it
	
	Color windowBackgroundColor = new Color(236, 86 , 62);
	Color windowBackgroundColor2 = new Color(51, 73, 96);
	
	public WidgetWindow(ApiResponse response)
	{
		this.weatherData = response;
		setWeatherData();
		frame.setVisible(true);
	}
	
	private JFrame initWindow()
	{
		JFrame frame = new JFrame("Desktop Weather Widget");
		
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 150;
		int startY = (screenSize.height - frameSize.width) - 250;
		
		this.temperatureLabel.setOpaque(false);
		this.temperatureLabel.setForeground(Color.WHITE);
		this.temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		this.temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
		this.temperatureLabel.setName("temperatureLabel");
		//temperatureLabel.setBounds(0, 15, 0, 0);
		
		this.weatherTypeLabel.setOpaque(false);
		this.weatherTypeLabel.setForeground(Color.WHITE);
		this.weatherTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		this.weatherTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.weatherTypeLabel.setName("weatherTypeLabel");
		//weatherTypeLabel.setBounds(0, 150, 14, 14);	
		
		this.countryCodeLabel.setOpaque(false);
		this.countryCodeLabel.setForeground(Color.WHITE);
		this.countryCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		this.countryCodeLabel.setHorizontalAlignment(JLabel.LEFT);
		this.countryCodeLabel.setName("countryCodeLabel");
		
		this.countryNameLabel.setOpaque(false);
		this.countryNameLabel.setForeground(Color.WHITE);
		this.countryNameLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		this.countryNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		this.countryNameLabel.setName("countryNameLabel");
		this.countryNameLabel.setText(weatherData.getCityName());
		
		frame.setMinimumSize(new Dimension(100,200));
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(new Color(236, 86 , 62));
		
		frame.setLocation(startX, startY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		//frame.setUndecorated(true);
		frame.setLayout(new BorderLayout(3,3));
		frame.add(temperatureLabel, BorderLayout.NORTH);
		frame.add(weatherTypeLabel, BorderLayout.CENTER);
		frame.add(countryCodeLabel, BorderLayout.SOUTH);
		frame.add(countryNameLabel, BorderLayout.SOUTH);
		
		return frame;
	}
	
	private void setWeatherData()
	{
		this.temperatureLabel.setText(weatherData.getTemperature() + "\u00b0C");
		this.weatherTypeLabel.setText(weatherData.getWeatherType());
		this.countryCodeLabel.setText(weatherData.getCountryCode() + ",");
		this.countryNameLabel.setText(weatherData.getCityName());
	}

}
