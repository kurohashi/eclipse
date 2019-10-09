package ui;

import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.GlobalDetails;

public class SigningFrame extends DefaultFrame {
	
	private static final long serialVersionUID = 1831102915827085684L;
	protected Container c;
	protected JLabel labelUsername, labelPassword;
	protected JPasswordField textBoxPassword;
	protected JTextField textBoxUsername;
	protected GroupLayout layout;
	
	public SigningFrame() {
		c = getContentPane();
		setBounds(new Rectangle((int)(width*3/8), (int)(height*3/8), (int)(width/4), (int)(height/4)));
		labelPassword = new JLabel("Password");
        labelUsername = new JLabel("Username");
        textBoxPassword = new JPasswordField();
        textBoxUsername = new JTextField();
        layout = new GroupLayout(c);
        c.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
	}
	
	public void setAccount() {
		GlobalDetails.setUserName(labelUsername.getText());
	}
}
