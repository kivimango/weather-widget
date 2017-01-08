package kivimango.weatherwidget.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * StatusBar class, displaying the animated .gif during the http requests, and
 * the application icon to open the application's github page.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class StatusPanel extends JPanel {
	
	private static final long serialVersionUID = 4447727101565396338L;
	
	String APP_URI = new String("https://github.com/kivimango/weather-widget");
	
	JLabel loadingLabel = new JLabel();
	
	JLabel infoLabel = new JLabel();
	
	boolean enableAnimation = false;
	
	// can't load gif resource as a BufferedImage, because it wont be animated
	
	URL urlToLoadingGif = this.getClass().getResource("/icons/loading.gif");
	
	ImageIcon iconLoading = new ImageIcon(urlToLoadingGif);
	
	public StatusPanel() {
		setBounds(0, 560, 200, 40);
		setOpaque(false);
		setLayout(new BorderLayout());
		
		// for padding
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		
		loadingLabel.setBounds(0, 560, 100, 40);
		loadingLabel.setOpaque(false);
			
		infoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
			
		infoLabel.setOpaque(false);
		infoLabel.setText("Website");
		infoLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	  if (e.getClickCount() == 1) {
	                      if (Desktop.isDesktopSupported()) {
	                            Desktop desktop = Desktop.getDesktop();
	                            try {
	                                URI uri = new URI(APP_URI);
	                                desktop.browse(uri);
	                            } catch (IOException ex) {
	                                ex.printStackTrace();
	                            } catch (URISyntaxException ex) {
	                                ex.printStackTrace();
	                            }
	                    }
	            }
	        }
		});
		
		add(loadingLabel, BorderLayout.WEST);
		add(infoLabel, BorderLayout.EAST);
	}
	
	public boolean isEnableAnimation() {
		return enableAnimation;
	}

	public void setEnableAnimation(boolean enableAnimation) {
		if(enableAnimation)
		{
			loadingLabel.setIcon(iconLoading);
		}
		else
		{
			loadingLabel.setIcon(null);
		}
	}
}
