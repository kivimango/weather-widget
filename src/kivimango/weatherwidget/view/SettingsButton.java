package kivimango.weatherwidget.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Setting up the Settings button in the top left corner.
 * Clicking on this button the application will pop up the Settings window
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class SettingsButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage buttonIcon;

	public SettingsButton() {
		super();
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		seticonFromResource();
		setToolTipText("Opens the settings window");
		setOpaque(false);
		
		addActionListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		WidgetWindow.settingsPanel.setVisible(true);
	}
	
	@Override
	public void setBorder(Border b) {}
	
	private void seticonFromResource()
	{
		try {
			buttonIcon = ImageIO.read(getClass().getResource("/buttons/gear.png"));;
			setIcon(new ImageIcon(buttonIcon));
		} catch (IOException | IllegalArgumentException e) {
			// TO-DO : Implement some warning to the user, eg.:broken app, missing file, resinstall it, etc
		}
	}
}
