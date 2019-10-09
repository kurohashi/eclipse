package ui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Enter extends DefaultFrame {

	JButton login, signUp;
	JLabel printImg;
	static int imgId;
	
	public Enter() {
		
		setBounds(new Rectangle((int)(width/4), (int)(height/4), (int)(width/2), (int)(height/2)));
		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        } 
		        catch(Exception ex){
		            ex.printStackTrace();
		        }
				new Login();			
			}
		});
		signUp = new JButton("Sign Up");	
		signUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        } 
		        catch(Exception ex){
		        	ex.printStackTrace();
		        }		        
				new SignUp();			
			}
		});
		login.setBounds(getWidth()/8, getHeight()/2 + getHeight()/4, getWidth()/4, 20);
		signUp.setBounds(getWidth()*5/8, getHeight()/2 + getHeight()/4, getWidth()/4, 20);
		login.setVisible(true);
		signUp.setVisible(true);
		add(login);
		add(signUp);
		repaint();
	}
	
	public static void main(String[] args) {
		new Enter();
	}
}
