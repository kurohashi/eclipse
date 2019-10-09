package Components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreateFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Container c,g;
	StringBuffer ImageURL = new StringBuffer("NewIcon.png");
	JComponent jce;
	public CreateFrame() {
		
		c = getContentPane();
		g = (Container) getGlassPane();
		URL iconURL = getClass().getResource(ImageURL.toString());
		final ImageIcon img = new ImageIcon(iconURL);
		setAlwaysOnTop(true);
		setVisible(true);
		c.setLayout(null);
		g.setLayout(null);
		c.setBackground(Color.cyan);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Samy");
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		double width = d.getWidth(); 
		double height = d.getHeight();
		setIconImage(img.getImage());
		setResizable(false);
		setBounds(new Rectangle((int)(width/2 - width/4), (int)(height/2 - height/4), (int)(width/2), (int)(height/2)));
//		setBounds(0,0,800,600);
//		addMouseMotionListener(this);
		
	}
	
	public void addComponent(JComponent e) {
		
		if (e instanceof JLabel) {
			
			this.c.add((JLabel)e);
			jce = e;
			
		} else if (e instanceof JButton) {
			
			this.c.add((JButton)e);
			jce = e;
			
		}
		
		this.repaint();
		
	}
	
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		if(e.getX() <= 70 && e.getY() <= 70) {
//			jce.setVisible(true);
//		}
//		
//	}
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		if(e.getX() <= 70 && e.getY() <= 70) {
//			jce.setVisible(false);
//			
//		}
//		
//	}
	
}
