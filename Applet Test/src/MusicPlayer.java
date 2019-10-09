import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MusicPlayer extends Applet implements ActionListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button b1;
	
	@Override
	public void init() {
		b1 = new Button("Play");
		add(b1);
		b1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AudioClip ac = getAudioClip(getDocumentBase(), "aa.wav");
		ac.play();
		Thread t = new Thread(this);
		
		t.start();
	}

	@Override
	public void run() {
		AudioClip ac = getAudioClip(getDocumentBase(), "04.wav");
		ac.play();
	}

}
