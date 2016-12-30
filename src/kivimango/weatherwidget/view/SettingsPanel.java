package kivimango.weatherwidget.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Setting up the Settings panel.
 * User can set the city option.
 * 
 * @author		kivimango	dev@kivimango.hu
 * @copyright	kivimango	
 * @license		MIT License	https://opensource.org/licenses/MIT
 * @link 		https://github.com/kivimango/weather-widget
 * @version		0.1
 * @since		0.1
 * @package 	kivimango.weatherwidget.view
 */

public class SettingsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7573988335607174452L;

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
		add(cityLabel, constraints);
		
		constraints.gridy = 1;
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
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Settings"));
		setMaximumSize(new Dimension(200,300));
		setPreferredSize(new Dimension(200,300));
		setMinimumSize(new Dimension(200,300));
		setOpaque(false);
		
		cancelButton.addActionListener(this);
		applyButton.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton)
		{
			setVisible(false);
		}
		else {
			// TO-DO : implement saving the settings
			setVisible(false);
		}
	}

}
