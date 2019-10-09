package socket;

import java.io.*;
import java.net.*;
import java.util.Properties;

import report.ErrorReport;
import ui.ChatBox;
import user.GlobalDetails;

public class SocketClient extends Thread {
    
    public int port;
    public String serverAddr, propertiesFileName;
    public Socket socket;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    private ChatBox chatBox;
    
    public SocketClient(ChatBox chatBox) {
    	
    	this.chatBox = chatBox;
    	try {
			propertiesFileName = "server.properties";
			File f = new File(propertiesFileName);
			FileInputStream fin = null;
			fin = new FileInputStream(f);
			Properties pr = new Properties();
			pr.load(fin);
			serverAddr = pr.getProperty("address"); 
			port = Integer.parseInt(pr.getProperty("port"));
		
			try {
				if (!serverAddr.isEmpty() && port == 0) {
					SocketClient client = new SocketClient(chatBox);
					client.start();
					client.send(new Message("test", "testUser", "testContent", "SERVER"));
				}
			} catch (Exception e) {
				ErrorReport.getReport("Server not found...");
			}
			
			socket = new Socket(InetAddress.getByName(serverAddr), port);
			Out = new ObjectOutputStream(socket.getOutputStream());
			Out.flush();
			In = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			ErrorReport.getReport(e.toString());
		}
        
    }

    @SuppressWarnings("deprecation")
	@Override
    public void run() {
    	
        boolean keepRunning = true;
        Message msg = null;
        
        while (keepRunning) {
            try {
                msg = (Message) In.readObject();
                System.out.println("Incoming : " + msg.toString());
                
                if (msg.getType().equals("message")) {
                	
                    if (msg.getRecipient().equals(GlobalDetails.getUserName())) {
                    	chatBox.setUpdateMessage(msg, 1);                        
                    } else {
                    	chatBox.setUpdateMessage(msg, 2);
                    }                    
                } else if(msg.getType().equals("login")) {
                	
                    if (msg.getContent().equals("TRUE")) {
                        chatBox.setUpdateMessage(msg, 3);
                    } else {
                    	chatBox.setUpdateMessage(msg, 4);
                    }
                } else if(msg.getType().equals("newuser")) {
                	
                    if (!msg.getContent().equals(GlobalDetails.getUserName())) {
                    	chatBox.setUpdateMessage(msg, 5);
                    }
                } else if(msg.getType().equals("signup")) {
                	
                    if (msg.getContent().equals("TRUE")) {
                    	
                        chatBox.setUpdateMessage(msg, 6);
                    } else {
                    	
                        chatBox.setUpdateMessage(msg, 7);
                    }
                } else if (msg.getType().equals("signout")) {
                	
                    chatBox.setUpdateMessage(msg, 8);
                    if (msg.getContent().equals(GlobalDetails.getUserName())) {
                    	this.stop();
                    }
                } else if (msg.getType().equals("upload_req")) {

                	if (chatBox.setUpdateMessage(msg, 9)) {
                		Download dwn = new Download(GlobalDetails.getFile().getPath(), chatBox);
                		Thread t = new Thread(dwn);
                		t.start();

                		send(new Message("upload_res", GlobalDetails.getUserName(), "" + dwn.port, msg.getSender()));
                	} else {

                		send(new Message("upload_res", GlobalDetails.getUserName(), "NO", msg.getSender()));
                	}

                } else if (msg.getType().equals("upload_res")) {
                	
                    if (!msg.getContent().equals("NO")) {
                    	
                        int port  = Integer.parseInt(msg.getContent());
                        String addr = msg.getSender();
                        
                        Upload upl = new Upload(addr, port, GlobalDetails.getFile(), chatBox);
                        Thread t = new Thread(upl);
                        t.start();
                    } else {
                    	
                        chatBox.setUpdateMessage(msg, 10);
                    }
                } else {
                    chatBox.setUpdateMessage(msg, 11);
                }
            } catch (Exception ex) {
            	
                keepRunning = false;
                ErrorReport.getReport("[Application > Me] : Connection Failure\n");
                
                chatBox.setUpdateMessage(msg, 12);
                
                this.stop();
                
                ErrorReport.getReport("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg) {
    	
        try {
        	
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : " + msg.toString());
            
        } catch (IOException ex) {
        	
            ErrorReport.getReport("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t) {
        t = null;
    }
}
