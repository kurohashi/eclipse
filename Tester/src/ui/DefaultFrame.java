package ui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame {
	
	protected double width;
	protected double height;
	protected Rectangle winSize;
	
	public DefaultFrame() {
		
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Arya Messenger");
		winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		width = winSize.getWidth(); 
		height = winSize.getHeight();
	}
}
