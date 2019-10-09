import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

class Copy extends Thread {

	boolean flag = false;
	long i = 0, j;
	UI frame;
	File f1, f2;
	FileInputStream fin;
	FileOutputStream fout;
	String sourceFileName;
	StringBuffer destinationFileName;
	
	public Copy(UI frame, String sourceFileName) {
		this.frame = frame;
		this.sourceFileName = sourceFileName;
		f1 = new File(sourceFileName);
		StringBuffer tempDestinationFileName = new StringBuffer(sourceFileName);
		tempDestinationFileName.reverse();
		destinationFileName = new StringBuffer();
		boolean flag = true;
		
		for (char ch : tempDestinationFileName.toString().toCharArray()) {
			destinationFileName.append(ch);
			
			if (ch == '.') {
				destinationFileName.append("ypoC_");
				flag = false;
			}
		}
		
		destinationFileName.reverse();
		
		if (flag) {
			destinationFileName.append("_Copy");
		}
		
		f2 = new File(destinationFileName.toString());
		
		j = f1.length();
		
		try {
			fin = new FileInputStream(f1);
			fout = new FileOutputStream(f2);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void run() {
				
		int z = 0;
		
		do {
			try {
				z = fin.read();
				fout.write(z);
				Thread.sleep(1);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		} while (z != -1);
		
		flag = true;
	}
	
}

class Display extends Thread {

	Copy c;
	UI frame;
	CopyStatus cs;
	
	public Display(Copy c, UI frame, CopyStatus cs) {
		this.c = c;
		this.frame = frame;
		this.cs = cs;
	}
	
	@Override
	public void run() {
		while (!c.flag) {
			frame.percentageCopied.setText(String.valueOf(c.i*100.0/c.j) + "% of file " + c.sourceFileName + " copied...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		frame.percentageCopied.setText("Copy Completed of file " + c.sourceFileName + " to " +  c.destinationFileName);
		frame.setTitle("Copy File : Completed");
		frame.statusButton.removeActionListener(cs);
		frame.removeKeyListener(cs);
	}
	
}

class CopyStatus implements KeyListener, ActionListener {
	
	Copy c;
	UI frame;
	StringBuffer stat;
	
	public CopyStatus(UI frame, Copy c) {
		
		this.c = c;
		this.frame = frame;
		stat = new StringBuffer("running");
		frame.addKeyListener(this);
		frame.statusButton.addActionListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (stat.toString().hashCode() == "running".hashCode()) {
			frame.statusButton.setText("Resume");
			frame.setTitle("Copy File : Paused");		
			stat.replace(0, stat.length(), "paused");
			c.suspend();
		} else {
			frame.statusButton.setText("Pause");
			frame.setTitle("Copy File : Running");	
			stat.replace(0, stat.length(), "running");
			c.resume();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void keyTyped(KeyEvent e) {
		if (stat.toString().hashCode() == "running".hashCode()) {
			frame.statusButton.setText("Resume");
			frame.setTitle("Copy File : Paused");	
			stat.replace(0, stat.length(), "paused");
			c.suspend();
		} else {
			frame.statusButton.setText("Pause");
			frame.setTitle("Copy File : Running");	
			stat.replace(0, stat.length(), "running");
			c.resume();
		}				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class UI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6276967444898612488L;
	
	JComponent jce;
	public Container c,g;
	JToggleButton statusButton;
	JTextField percentageCopied;
	
	public UI() {
		c = getContentPane();
		g = (Container) getGlassPane();
		setAlwaysOnTop(true);
		setVisible(true);
		c.setLayout(null);
		g.setLayout(null);
		c.setBackground(Color.cyan);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Copy File : Running");
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		double width = d.getWidth(); 
		double height = d.getHeight();
		setResizable(false);
		setBounds(new Rectangle((int)(width/2 - width/4), (int)(height/2 - height/4), (int)(width/2), (int)(height/2)));
		statusButton = new JToggleButton("Pause");
		percentageCopied = new JTextField("0");
		add(statusButton);
		add(percentageCopied);
		statusButton.setBounds(this.getWidth()/2-50, (int) (this.getHeight()*0.75), 100, 20);
		percentageCopied.setBounds(0, 0, getWidth(), getHeight()/5);
		statusButton.setVisible(true);
		percentageCopied.setVisible(true);
		statusButton.setFocusable(true);
	}
}

public class CopyFile {

	public static void main(String[] args) {
		
		String sourceFileName = new String("D:\\Demo.jpg");
		
		UI u = new UI();
		Copy c = new Copy(u, sourceFileName);
		CopyStatus cs = new CopyStatus(u, c);
		Display d = new Display(c, u, cs);
		
		c.start();
		d.start();
		
		
	}

}
