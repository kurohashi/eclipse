package report;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorReport extends JFrame {

	private static final long serialVersionUID = -7538233077496503595L;
	private JLabel error = new JLabel();
	
	private ErrorReport(String e) {
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Arya Messenger");
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		double width = d.getWidth(); 
		double height = d.getHeight();
		setBounds(new Rectangle((int)(width*3/8), (int)(height*3/8), (int)(width/4), (int)(height/4)));
		error.setBounds(getWidth()/8, getHeight()/5, getWidth()*7/8, getHeight()*2/5);
		error.setText(e);
		add(error);
		error.setVisible(true);
		repaint();
	}
	
	public static void getReport(String e) {
		new ErrorReport(e);
	}
}
