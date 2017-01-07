package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import kivimango.weatherwidget.model.WeatherForecast;

/**
 * Container component for displaying the forecast data in 3 column
 * 
 * @author 		kivimango
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class ForecastPanel extends JPanel {
	
	private static final long serialVersionUID = -34479900273371877L;
	
	Calendar calendar = Calendar.getInstance();

	private List<WeatherForecast> forecastData;
	
	JPanel columnA = new JPanel();
	JPanel columnB = new JPanel();
	JPanel columnC = new JPanel();
	
	public ForecastPanel(List<WeatherForecast> dataToSet) throws MalformedURLException {
		setBounds(0, 250, 200, 200);
		setLayout(new GridLayout());
		setOpaque(false);
	
		forecastData = dataToSet;
		
		columnA = makeGrid(forecastData.get(0));
		columnB = makeGrid(forecastData.get(1));
		columnC = makeGrid(forecastData.get(2));
		
		add(columnA);
		add(columnB);
		add(columnC);
	}
	
	private JPanel makeGrid(WeatherForecast data) throws MalformedURLException
	{
		JPanel grid = new JPanel();
		
		JLabel timeLabel = new JLabel();
		JLabel temperatureLabel = new JLabel();
		JLabel iconLabel = new JLabel();
		JTextArea descLabel = new JTextArea();
		
		calendar.setTime(data.getTime());
		
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.white);
		timeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		timeLabel.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(calendar.get(Calendar.MINUTE)) + Integer.toString(calendar.get(Calendar.MINUTE)));
		timeLabel.setPreferredSize(new Dimension(65, 50));

		temperatureLabel.setForeground(Color.white);
		temperatureLabel.setText(Double.toString(data.getTemperature()) + "\u00b0C");
		
		iconLabel.setIcon(new ImageIcon(new URL(data.getIcon())));
		
		descLabel.setEditable(false);
		descLabel.setLineWrap(true);
		descLabel.setForeground(Color.white);
		descLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		descLabel.setOpaque(false);
		descLabel.setText(data.getDescription());
		descLabel.setPreferredSize(new Dimension(65, 100));
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
}
