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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	JLabel titleLabel = new JLabel("Forecast");
	JPanel gridContainer = new JPanel();
	
	JPanel columnA = new JPanel();
	JPanel columnB = new JPanel();
	JPanel columnC = new JPanel();
	
	public ForecastPanel(List<WeatherForecast> dataToSet) throws MalformedURLException {
		setBounds(0, 250, 200, 200);
		setLayout(new FlowLayout());
		setOpaque(false);
		
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(false);
		titleLabel.setPreferredSize(new Dimension(180,50));
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		// for drawing a bottom border only
		titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		gridContainer.setLayout(new GridLayout());
		gridContainer.setOpaque(false);
		
		forecastData = dataToSet;
		
		columnA = makeGrid(forecastData.get(0));
		columnB = makeGrid(forecastData.get(1));
		columnC = makeGrid(forecastData.get(2));
		
		add(titleLabel);
		gridContainer.add(columnA);
		gridContainer.add(columnB);
		gridContainer.add(columnC);
		add(gridContainer);
	}
	
	/**
	 * 
	 * @param data the WeatherForecast class from the getForecast() method's result list
	 * @return the generated JPanel grid displaying the forecast data
	 * @throws MalformedURLException
	 */
	
	private JPanel makeGrid(WeatherForecast data) throws MalformedURLException
	{
		JPanel grid = new JPanel();
		
		JLabel timeLabel = new JLabel();
		JLabel temperatureLabel = new JLabel();
		JLabel iconLabel = new JLabel();
		
		calendar.setTime(data.getTime());
		
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.white);
		timeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		timeLabel.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(calendar.get(Calendar.MINUTE)) + Integer.toString(calendar.get(Calendar.MINUTE)));

		iconLabel.setIcon(new ImageIcon(new URL(data.getIcon())));
		iconLabel.setToolTipText(data.getDescription());
		
		temperatureLabel.setForeground(Color.white);
		temperatureLabel.setText(new Double(data.getTemperature()).intValue() + "\u00b0C");
		
		grid.add(timeLabel);
		grid.add(iconLabel);
		grid.add(temperatureLabel);
		
		grid.setLayout(new FlowLayout(FlowLayout.CENTER));
		grid.setPreferredSize(new Dimension(65, 150));
		grid.setOpaque(false);
		
		return grid;
	}
}
