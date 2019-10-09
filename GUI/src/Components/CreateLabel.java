package Components;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CreateLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StringBuffer imageURL = new StringBuffer("Label.png");
	public CreateLabel(String string) {
		
		URL image = getClass().getResource(imageURL.toString());
		final ImageIcon img = new ImageIcon(image);
		img.setDescription(string);
		setBounds(0, 0, 70, 70);
//		setText(string);
		setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 18));
		setVisible(true);
		setIcon(img);
		setFocusable(true);
		addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseEntered(MouseEvent e) {
				setVisible(false);
				repaint();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setVisible(true);;
				repaint();
				
			}
			
		}); 

	}
		
}
