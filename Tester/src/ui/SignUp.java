package ui;

import javax.swing.GroupLayout.Alignment;

import socket.Message;
import socket.SocketClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class SignUp extends SigningFrame {

	private static final long serialVersionUID = -1004635773602792697L;
	private JLabel labelConfirmPassword;	
	private JPasswordField textBoxConfirmPassword;
	private JButton buttonSignUp;
	private SocketClient socket;
	
	public SignUp() {
		
		labelConfirmPassword = new JLabel("Confirm Password");
		textBoxConfirmPassword = new JPasswordField();
		buttonSignUp = new JButton("Sign Up");
		
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGap(50)
						.addGroup(layout.createParallelGroup()
								.addComponent(labelUsername)
								.addComponent(labelPassword)
								.addComponent(labelConfirmPassword))
						.addGap(30)
						.addGroup(layout.createParallelGroup()
								.addComponent(textBoxUsername)
								.addComponent(textBoxPassword)
								.addComponent(textBoxConfirmPassword))
						.addGap(50))
				.addComponent(buttonSignUp));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(labelUsername)
						.addComponent(textBoxUsername))
				.addGap(12)
				.addGroup(layout.createParallelGroup()
						.addComponent(labelPassword)
						.addComponent(textBoxPassword))
				.addGap(12)
				.addGroup(layout.createParallelGroup()
						.addComponent(labelConfirmPassword)
						.addComponent(textBoxConfirmPassword))
				.addGap(12)
				.addComponent(buttonSignUp));
		repaint();
		buttonSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				signUpActionPerformed();				
			}
		});
	}
	
	private void signUpActionPerformed() {
		socket.send(new Message("signup", labelUsername.getText(), labelPassword.getText(), "SERVER"));
		setAccount();
	}
}
