package kivimango.weatherwidget.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import kivimango.weatherwidget.controller.WeatherWidget;
import kivimango.weatherwidget.model.Settings;
import kivimango.weatherwidget.model.SettingsLoader;

/**
 * Setting up the Settings panel.
 * User can set the city option.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		GNU General Public License v3	https://www.gnu.org/licenses/gpl-3.0.html
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class SettingsPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 7573988335607174452L;

	private static SettingsLoader settingsLoader = new SettingsLoader();
	private static Settings settings = settingsLoader.getSettings();
	
	JLabel cityLabel = new JLabel("Enter your city:");
	JTextField cityTextField = new JTextField(10);
	JButton cancelButton = new JButton("Cancel");
	JButton applyButton = new JButton("Apply");
	
	public SettingsPanel() {
		super();
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		cityLabel.setForeground(Color.black);
		add(cityLabel, constraints);
		
		constraints.gridy = 1;
		cityTextField.setText(settings.getCity());
		add(cityTextField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		
		add(cancelButton, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		
		add(applyButton, constraints);
		
		TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Settings", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 13), Color.black);
		setBackground(Color.white);
		setBorder(titledBorder);
		setBounds(0, 200, 200, 300);
		setPreferredSize(new Dimension(200,300));
		setOpaque(true);
		
		cancelButton.addActionListener(this);
		applyButton.addActionListener(this);
		
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton)
		{
			setVisible(false);
		}
		else if((e.getSource() == applyButton)) {
			settings.setCity(cityTextField.getText());
			settingsLoader.saveSettingsFile(settings);
			try {
				WeatherWidget.reload();
			} catch (JsonIOException | JsonSyntaxException | IOException e1) {
				// user entered invalid String, using the default setting...
				settings.setCity(new Settings().getCity());
			}
			setVisible(false);
		}
	}
}
