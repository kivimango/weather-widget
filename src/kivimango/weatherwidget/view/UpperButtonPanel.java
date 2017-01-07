package kivimango.weatherwidget.view;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * Container component for the buttons in the top right corner of the main JFrame
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

	public UpperButtonPanel() {
		add(settingsButton);
		add(closeButton);
		setBounds(0, 0, 200, 50);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setOpaque(false);
	}
}
