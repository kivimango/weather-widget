package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Container component for the displaying the weather details under the UpperButtonPanel 
 * 
 * @author 		kivimango
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class DetailPanel extends JPanel {

	private static final long serialVersionUID = 9140829122466542109L;
	
	JLabel weatherIcon		= new JLabel();
	JLabel weatherTypeLabel = new JLabel();
	JLabel temperatureLabel = new JLabel();

	public DetailPanel() {
		setBounds(0, 50, 200, 200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setOpaque(false);
		
		weatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
		weatherIcon.setMinimumSize(new Dimension(50, 100));
		weatherIcon.setMaximumSize(new Dimension(50, 100));
		weatherIcon.setOpaque(false);
		weatherIcon.setPreferredSize(new Dimension(50, 100));
		
		add(weatherIcon);
		
		weatherTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		weatherTypeLabel.setForeground(Color.WHITE);
		weatherTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		weatherTypeLabel.setMinimumSize(new Dimension(130, 100));
		weatherTypeLabel.setMaximumSize(new Dimension(130, 100));
		weatherTypeLabel.setOpaque(false);
		weatherTypeLabel.setPreferredSize(new Dimension(130, 100));
		
		add(weatherTypeLabel);
		
		temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		temperatureLabel.setForeground(Color.WHITE);
		temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 44));
		temperatureLabel.setMinimumSize(new Dimension(200, 50));
		temperatureLabel.setMaximumSize(new Dimension(200, 50));
		temperatureLabel.setOpaque(false);
		temperatureLabel.setPreferredSize(new Dimension(200, 50));
		
		add(temperatureLabel);
	}
}
