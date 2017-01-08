package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Container component for the buttons and a city label in the top of the main JFrame
 * 
 * @author		kivimango
 * @copyright	kivimango	https://github.com/kivimango/weather-widget
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class UpperButtonPanel extends JPanel {
	
	private static final long serialVersionUID = -1548235876309008000L;
	
	CloseButton closeButton = new CloseButton();
	SettingsButton settingsButton = new SettingsButton();
	JLabel countryNameLabel = new JLabel();
	
	public UpperButtonPanel() {
		setBounds(0, 0, 200, 100);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setOpaque(false);
		
		countryNameLabel.setBounds(25, 0, 150, 50);
		countryNameLabel.setForeground(Color.WHITE);
		countryNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
		countryNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		countryNameLabel.setOpaque(false);
		
		add(settingsButton, FlowLayout.LEFT);
		add(countryNameLabel, FlowLayout.CENTER);
		add(closeButton, FlowLayout.RIGHT);
	}
}
