package com.socket;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class ServerFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1528306293930559689L;
	public SocketServer server;
	public Thread serverThread;
	public String filePath = "D:\\Data.xml";	

	private JButton jButton1;
	private JScrollPane jScrollPane1;
	public JTextArea jTextArea1;

	public ServerFrame() {
		initComponents();
		jTextArea1.setEditable(false);
	}

	public boolean isWin32() {
		return System.getProperty("os.name").startsWith("Windows");
	}
	private void initComponents() {

		jButton1 = new JButton();
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Arya Secured Server");

		jButton1.setText("Start Server");
		jButton1.setEnabled(true);
		jButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)						
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
				.addComponent(jButton1)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
				.addComponent(jButton1)	
		);
		pack();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		server = new SocketServer(this);
		jButton1.setEnabled(false);
	}

	public void RetryStart(int port){
		if(server != null) { 
			server.stop(); 
		}
		server = new SocketServer(this, port);
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception ex) {
			System.out.println("Look & Feel Exception");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerFrame().setVisible(true);
			}
		});
	}
}
