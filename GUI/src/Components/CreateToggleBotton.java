package Components;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CreateToggleBotton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateToggleBotton(int fx, int fy) {
		
		setBounds(new Rectangle(new Point(fx - 66,fy - 79), new Dimension(60,50)));
		final StringBuffer status = new StringBuffer("t");
		URL initialImage = getClass().getResource("ei.jpg");
		URL finalImage = getClass().getResource("ef.png");
		final ImageIcon ei = new ImageIcon(initialImage);
		final ImageIcon ef = new ImageIcon(finalImage);
		setIcon(ei);
		setFocusable(true);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (status.toString().startsWith("t")) {
					setIcon(ef);
					status.replace(0, 1, "f");
				} else if (status.toString().startsWith("f")) {
					setIcon(ei);
					status.replace(0, 1, "t");
				} else {
					System.out.println("wrong status...");
				}
				
			}
		});
		setVisible(true);
		
	}
	
}
