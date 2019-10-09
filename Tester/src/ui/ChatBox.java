package ui;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import report.ErrorReport;
import socket.Message;
import socket.SocketClient;
import user.GlobalDetails;

public class ChatBox extends DefaultFrame {
	
	private static final long serialVersionUID = 5194616124895935758L;
	private Container c;
	private JFileChooser chooseFileToSend;
	private JScrollPane messagePane, typeMessagePane;
	private JList selectorList;
	private DefaultListModel model;
	private JButton send, fileChooser;
	private JToggleButton selector;
	private JTextArea typeMessageBox, messageArea;
	private File f;
	private GroupLayout layout;
	private SocketClient socket;
	
	public ChatBox() {
		
		socket = new SocketClient(this);
		c = getContentPane();
		layout = new GroupLayout(c);
		c.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
        	ex.printStackTrace();
        }		
		
		selector = new JToggleButton("=");
		selector.setToolTipText("Click to select resipient.");
		selector.setSelected(false);
		selector.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selector.isSelected()) {
					setExtendedLayout();
				} else {
					setNormalLayout();
				}
			}
		});
		
		messageArea = new JTextArea();
		messagePane = new JScrollPane(messageArea);
		
		send = new JButton("Send");
		send.setVisible(true);
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeMessageBox.getText() == "File Attached : " + f.getPath() || typeMessageBox.getText() == "" && chooseFileToSend.isTraversable(f)) {
					socket.send(new Message("upload_req", GlobalDetails.getUserName(), f.getName(), selectorList.getSelectedValue().toString()));
				} else if (typeMessageBox.getText() == "File Attached : " + f.getPath() && !chooseFileToSend.isTraversable(f)) {
					typeMessageBox.setText("");
					ErrorReport.getReport("No file is selected...");
				} else {
					socket.send(new Message("message", GlobalDetails.getUserName(), typeMessageBox.getText(), selectorList.getSelectedValue().toString()));
				}
			}
		});
		
		typeMessageBox = new JTextArea();
		typeMessagePane = new JScrollPane(typeMessageBox);
		
		String ImageURL = "Clip.png";
		fileChooser = new JButton(new ImageIcon(getClass().getResource(ImageURL)));
		fileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFileToSend = new JFileChooser();
		        chooseFileToSend.showOpenDialog(null);
		        f = chooseFileToSend.getSelectedFile();
		        
		        if (f != null && !f.getName().isEmpty()) {
		        	typeMessageBox.setText("File Attached : " + f.getPath());
		        }				
			}
		});
		
		selectorList = new JList(model = new DefaultListModel());
		
		setNormalLayout();
		repaint();
	}
	
	private void setNormalLayout() {
		setBounds(new Rectangle((int)(width*3/4), (int)(height/3), (int)(width/4), (int)(height*2/3)));
		
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(selector)
				.addComponent(messagePane)
				.addGroup(layout.createSequentialGroup()
						.addComponent(typeMessagePane)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(send)
								.addComponent(fileChooser))));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(selector)
				.addComponent(messagePane)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeMessagePane, 55, 55 ,55)
						.addGroup(layout.createSequentialGroup()
								.addComponent(send)
								.addComponent(fileChooser))));
		
		selectorList.setVisible(false);
	}
	
	private void setExtendedLayout() {
		setBounds(new Rectangle((int)(width*3/4) - 120, (int)(height/3), (int)(width/4) + 120, (int)(height*2/3)));
		
		selectorList.setVisible(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectorList, 116, 116, 116)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(selector)
								.addComponent(messagePane)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(typeMessagePane)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(send)
								.addComponent(fileChooser))));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(selectorList)
						.addGroup(layout.createSequentialGroup()
								.addComponent(selector)
								.addComponent(messagePane)))
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeMessagePane, 55, 55, 55)
						.addGroup(layout.createSequentialGroup()
								.addComponent(send)
								.addComponent(fileChooser))));
	}
	
	public boolean setUpdateMessage(Message msg, int status) {
		
		if (status == 1) {
			messageArea.append("["+msg.getSender() +" > Me] : " + msg.getContent() + "\n");
		} else if (status == 2) {
			messageArea.append("["+ msg.getSender() +" > "+ msg.getRecipient() +"] : " + msg.getContent() + "\n");
		} else if (status == 3) {
			messageArea.append("[SERVER > Me] : Login Successful\n");
		} else if (status == 4) {
			messageArea.append("[SERVER > Me] : Login Failed\n");
		} else if (status == 5) {
			boolean exists = false;
			for (int i = 0; i < model.getSize(); i++) {
				if (model.getElementAt(i).equals(msg.getContent())) {
					exists = true; 
					break;
				}
			}
			if (!exists) {
				model.addElement(msg.getContent()); 
			}
		} else if (status == 6) {
			messageArea.append("[SERVER > Me] : Singup Successful\n");
		} else if (status == 7) {
			messageArea.append("[SERVER > Me] : Signup Failed\n");
		} else if (status == 8) {
			if (msg.getContent().equals(GlobalDetails.getUserName())) {
                messageArea.append("["+ msg.getSender() +" > Me] : Bye\n");
                
                for(int i = 1; i < model.size(); i++){
                	model.removeElementAt(i);
                }
            } else {
                model.removeElement(msg.getContent());
                messageArea.append("["+ msg.getSender() +" > All] : "+ msg.getContent() +" has signed out\n");
            }
		} else if (status == 9) {
			if (JOptionPane.showConfirmDialog(this, ("Accept '"+msg.getContent()+"' from "+msg.getSender()+" ?")) == 0) {
                
                JFileChooser jf = new JFileChooser();
                File file = new File(msg.getContent());
                GlobalDetails.setFile(file);
                jf.setSelectedFile(file);
                int returnVal = jf.showSaveDialog(this);
                
                return file != null && returnVal == JFileChooser.APPROVE_OPTION;
			} else {
            	
                socket.send(new Message("upload_res", GlobalDetails.getUserName(), "NO", msg.getSender()));
            }
		} else if (status == 10) {
			messageArea.append("[SERVER > Me] : "+msg.getSender()+" rejected file request\n");
		} else if (status == 11) {
			messageArea.append("[SERVER > Me] : Unknown message type\n");
		} else if (status == 12) {	
			
			for (int i = 1; i < model.size(); i++) {
                model.removeElementAt(i);
            }
		} else if (status == 13) {
			messageArea.append("[Applcation > Me] : File upload complete\n");
		} else if (status == 14) {
			messageArea.append("[Application > Me] : Download complete\n");
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		new ChatBox();
	}
}
