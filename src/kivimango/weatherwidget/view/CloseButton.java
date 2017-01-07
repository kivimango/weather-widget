package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Setting up the fully transparent Close button in the top right corner.
 * Clicking on this button the application will exit with the code 0.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class CloseButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 3618737918701167479L;
	private BufferedImage buttonIcon;

	public CloseButton() {
		super();
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFont(new Font("SansSerif", Font.BOLD, 18));
		setForeground(Color.WHITE);
		seticonFromResource();
		setOpaque(false);
		setToolTipText("Closes the application");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 System.exit(0);
	}
	
	private void seticonFromResource()
	{
		try {
			buttonIcon = ImageIO.read(getClass().getResource("/buttons/close.png"));;
			setIcon(new ImageIcon(buttonIcon));
		} catch (IOException | IllegalArgumentException e) {
			// TO-DO : Implement some warning to the user, eg.:broken app, missing file, resinstall it, etc
		}
	}

}
