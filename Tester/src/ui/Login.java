package ui;

import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import socket.Message;
import socket.SocketClient;

public class Login extends SigningFrame {

	private static final long serialVersionUID = 1838439995215361394L;
	private JButton buttonLogin;
	private SocketClient socket;

	public Login() {

		buttonLogin = new JButton("Login");

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGap(50)
						.addGroup(layout.createParallelGroup()
								.addComponent(labelUsername)
								.addComponent(labelPassword))
						.addGap(50)
						.addGroup(layout.createParallelGroup()
								.addComponent(textBoxUsername)
								.addComponent(textBoxPassword))
						.addGap(50))
				.addComponent(buttonLogin)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(35)
				.addGroup(layout.createParallelGroup()
						.addComponent(labelUsername)
						.addComponent(textBoxUsername))
				.addGap(20)
				.addGroup(layout.createParallelGroup()
						.addComponent(labelPassword)
						.addComponent(textBoxPassword))
				.addGap(20)
				.addComponent(buttonLogin)
				);
		repaint();
		
		buttonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed();				
			}
		});
	}
	
	private void loginActionPerformed() {
		socket.send(new Message("login", labelUsername.getText(), labelPassword.getText(), "SERVER"));
		setAccount();
		
	}
}
