/**
 * Setting up the fully transparent Close button in the top right corner.
 * Clicking on this button the application will exit with the code 0.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CloseButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 3618737918701167479L;

	public CloseButton() {
		super();
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFont(new Font("SansSerif", Font.BOLD, 18));
		setForeground(Color.WHITE);
		setOpaque(false);
		setText("X");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 System.exit(0);
	}

}
