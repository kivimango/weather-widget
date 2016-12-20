package kivimango.weatherwidget.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class WidgetWindow {
	
	JFrame frame = initWindow();
	
	public WidgetWindow()
	{
		frame.setVisible(true);
	}
	
	private JFrame initWindow()
	{
		JFrame frame = new JFrame("Desktop Weather Widget");
		
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int startX = (screenSize.width - frameSize.width) - 225;
		int startY = (screenSize.height - frameSize.width) - 150;
		
		frame.setMinimumSize(new Dimension(200,100));
		frame.setLocation(startX, startY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		return frame;
	}

}
