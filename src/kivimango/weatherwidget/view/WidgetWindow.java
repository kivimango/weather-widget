package kivimango.weatherwidget.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kivimango.weatherwidget.model.ApiResponse;
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

public class WidgetWindow {
	
	ApiResponse weatherData = new ApiResponse();
    
	JPanel upperButtonPanel = new JPanel();
	
	JPanel detailPanel = new JPanel();
	
	CloseButton closeButton = new CloseButton();
	
	JLabel temperatureLabel = new JLabel();
	JLabel weatherTypeLabel = new JLabel();
	JLabel countryCodeLabel = new JLabel();
	JLabel countryNameLabel = new JLabel();
	
	JFrame frame = initWindow();
	
	Color windowBackgroundColor = new Color(236, 86 , 62);
	Color windowBackgroundColor2 = new Color(51, 73, 96);
	
	public WidgetWindow(ApiResponse response)
	{
		weatherData = response;
		setWeatherData();
		setTransparency(4);
		
		//JLabel background = new JLabel(new ImageIcon("D:\\foggy_2_hd.jpg"));
		
		//upperButtonPanel.add(background);
		
		frame.setVisible(true);
	}
	
	private JFrame initWindow()
	{
		JFrame frame = new JFrame("Desktop Weather Widget");
		
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 275;
		int startY = (screenSize.height - frameSize.height) - 650;
		
		upperButtonPanel.add(closeButton, JPanel.RIGHT_ALIGNMENT);
		upperButtonPanel.setOpaque(false);
		upperButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		detailPanel.setOpaque(false);
		
		frame.add(upperButtonPanel);
		
		this.temperatureLabel.setOpaque(false);
		this.temperatureLabel.setForeground(Color.WHITE);
		this.temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		this.temperatureLabel.setHorizontalAlignment(JLabel.LEFT);
		this.temperatureLabel.setName("temperatureLabel");
		
		detailPanel.add(temperatureLabel);
		
		this.weatherTypeLabel.setOpaque(false);
		this.weatherTypeLabel.setForeground(Color.WHITE);
		this.weatherTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		this.weatherTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.weatherTypeLabel.setName("weatherTypeLabel");
		
		detailPanel.add(weatherTypeLabel);
		
		this.countryCodeLabel.setOpaque(false);
		this.countryCodeLabel.setForeground(Color.WHITE);
		this.countryCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		this.countryCodeLabel.setHorizontalAlignment(JLabel.LEFT);
		this.countryCodeLabel.setName("countryCodeLabel");
		
		detailPanel.add(countryCodeLabel);
		
		this.countryNameLabel.setOpaque(false);
		this.countryNameLabel.setForeground(Color.WHITE);
		this.countryNameLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		this.countryNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		this.countryNameLabel.setName("countryNameLabel");
		this.countryNameLabel.setText(weatherData.getCityName());
		
		detailPanel.add(countryNameLabel);
		
		frame.add(detailPanel);
		
		frame.setMinimumSize(new Dimension(250,600));
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(Color.black);
		frame.setLocation(startX, startY);
		frame.setResizable(true);
		frame.setUndecorated(true);
		frame.setLayout(new GridLayout(4,0));
		
		return frame;
	}
	
	private void setWeatherData()
	{
		temperatureLabel.setText(weatherData.getTemperature() + "\u00b0C");
		weatherTypeLabel.setText(weatherData.getWeatherType());
		countryCodeLabel.setText(weatherData.getCountryCode() + ",");
		countryNameLabel.setText(weatherData.getCityName());
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
		frame.setOpacity(Float.parseFloat(sOpacity));
	}
}
