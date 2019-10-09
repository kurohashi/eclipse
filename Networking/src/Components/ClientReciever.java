package Components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ClientReciever {

	public ClientReciever() {
		
		byte[] addr = {127, 0, 0, 1};
		Socket s = null;
		try {
			s = new Socket(InetAddress.getByAddress(addr), 1500);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream in =null;
		
		try {
			in = s.getInputStream();
		} catch (IOException e) {
			System.out.println("File unable to read.");
		}
		
		String savedLocation= null;
		JFileChooser choose = new JFileChooser(".");
		choose.setAcceptAllFileFilterUsed(false);
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		choose.setDialogTitle("Save In");
		choose.setVisible(true);
		
		if(choose.showOpenDialog(new JFrame("Save In")) == JFileChooser.APPROVE_OPTION) {
			File temp = choose.getSelectedFile();
			savedLocation = temp.getPath();
			System.out.println("Saved in : " + savedLocation);
		} else {
			System.out.println("Choose a folder....");
		}
		
		File newFile = new File(savedLocation + "New");
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(newFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int a = 0;
		while (a != -1) {
			try {
				a = in.read();
				fout.write(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
				
	}

}
