import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Apple extends JApplet {
	@Override
	public void init() {
		try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JLabel lbl = new JLabel("Hello World");
                    add(lbl);
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
	}
}
