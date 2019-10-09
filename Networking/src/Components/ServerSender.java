package Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ServerSender {

	public ServerSender() {
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(1500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String savedLocation= null;
		JFileChooser choose = new JFileChooser(".");
		choose.setAcceptAllFileFilterUsed(false);
		choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choose.setDialogTitle("Select File");
		choose.setVisible(true);
		
		if(choose.showOpenDialog(new JFrame("Select File")) == JFileChooser.APPROVE_OPTION) {
			File temp = choose.getSelectedFile();
			savedLocation = temp.getPath();
//			System.out.println("Saved in : " + savedLocation);
		} else {
			System.out.println("Choose a file....");
		}
		
		File newFile = new File(savedLocation);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(newFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

}
