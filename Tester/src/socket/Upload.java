package socket;

import ui.ChatBox;
import java.io.*;
import java.net.*;

public class Upload implements Runnable{

	public String addr;
	public int port;
	public Socket socket;
	public FileInputStream In;
	public OutputStream Out;
	public File file;
	public ChatBox chatBox;

	public Upload(String addr, int port, File filepath, ChatBox chatBox){
		super();
		try {
			file = filepath; 
			this.chatBox = chatBox;
			socket = new Socket(InetAddress.getByName(addr), port);
			Out = socket.getOutputStream();
			In = new FileInputStream(filepath);
		} 
		catch (Exception ex) {
			System.out.println("Exception [Upload : Upload(...)]");
		}
	}

	@Override
	public void run() {
		try {       
			byte[] buffer = new byte[1024];
			int count;

			while((count = In.read(buffer)) >= 0){
				Out.write(buffer, 0, count);
			}
			Out.flush();

			chatBox.setUpdateMessage(null, 13);

			if (In != null) { 
				In.close(); 
			}
			if (Out != null) { 
				Out.close(); 
			}
			if (socket != null) { 
				socket.close(); 
			}
		}
		catch (Exception ex) {
			System.out.println("Exception [Upload : run()]");
			ex.printStackTrace();
		}
	}

}