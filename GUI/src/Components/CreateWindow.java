package Components;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class CreateWindow extends JWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComponent jce;
	JLabel centerIcon;
	String IconURL = "NewIcon.png";

	public CreateWindow() {
		
		setLayout(null);
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = sSize.getWidth();
		double height = sSize.getHeight();
		setBounds((int)(width/2 - width/4), (int)(height/2 - height/4), (int)(width/2), (int)(height/2));
		URL url = getClass().getResource(IconURL);
		ImageIcon icon = new ImageIcon(url);
		centerIcon = new JLabel(icon);
		centerIcon.setBounds(getWidth()/2 - getHeight()/4, getHeight()/2 - getHeight()/4, getHeight()/2, getHeight()/2);
		setVisible(true);
		add(centerIcon);
		centerIcon.setVisible(true);
						
	}

}
