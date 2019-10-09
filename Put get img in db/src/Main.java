import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -722312652795781101L;
	Container c,g;
	JButton upload, download;
	JFileChooser fileChooser;
	JFrame ch;
	JLabel printImg;
	static int imgId;
	
	public Main() {
		c = getContentPane();
		g = (Container) getGlassPane();
		setVisible(true);
		c.setLayout(null);
		g.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Form");
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		double width = d.getWidth(); 
		double height = d.getHeight();
		setBounds(new Rectangle((int)(width/2 - width/4), (int)(height/2 - height/4), (int)(width/2), (int)(height/2)));
		upload = new JButton("Upload");
		download = new JButton("Download");		
		upload.addActionListener(new ActionListener() {
									
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("D:\\"));
				fileChooser.setAcceptAllFileFilterUsed(true);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle("Choose");
				String imgUrl = null;
				
				if(fileChooser.showOpenDialog(new JFrame("Choose")) == JFileChooser.APPROVE_OPTION) {
					File temp = fileChooser.getSelectedFile();
					imgUrl = temp.getPath();
					System.out.println(imgUrl);
				} else {
					System.out.println("Choose a file....");
				}

				try {
					ConnectToDatabase.putImage(imgId++, imgUrl);
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFound");
					System.out.println("Image couldn't be uploaded...");
				} catch (IOException e1) {
					System.out.println("IO Problem");
					System.out.println("Image couldn't be uploaded...");
				} catch (SQLException e1) {
					System.out.println("SQL Problem");
					System.out.println("Image couldn't be uploaded...");
				}				
				
			}
		});
		
		download.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String imageUrl = null;
				try {
					imageUrl = ConnectToDatabase.getImage(imgId-1);
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFound");
					System.out.println("Image couldn't be downloaded...");
				} catch (IOException e1) {
					System.out.println("IO Problem");
					System.out.println("Image couldn't be downloaded...");
				} catch (SQLException e1) {
					System.out.println("SQL Problem");
					System.out.println("Image couldn't be downloaded...");
				}
				
				printImg = new JLabel();
				URL image = getClass().getResource(imageUrl);
				ImageIcon img = new ImageIcon(image);
				printImg.setBounds(getWidth()/4, 20, getWidth()/2, getHeight()/2);
				printImg.setVisible(true);
				printImg.setIcon(img);
				printImg.setFocusable(true);
					
			}
		});
		upload.setBounds(getWidth()/2 - (3*getWidth())/8, getHeight()/2 + getHeight()/4, getWidth()/4, 20);
		download.setBounds(getWidth()/2 + getWidth()/8, getHeight()/2 + getHeight()/4, getWidth()/4, 20);
		upload.setVisible(true);
		download.setVisible(true);
		add(upload);
		add(download);
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
