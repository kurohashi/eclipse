package Components;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

public class LayerLayout {
	public LayerLayout() {
		CreateFrame newFrame = new CreateFrame();
		JLayeredPane pan = new JLayeredPane();
		JScrollPane pan1 = new JScrollPane(pan);
		newFrame.add(pan1);
		pan1.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
		pan.add(new JLabel("jbjbjbjb"));
		
		pan1.setVisible(true);
	}

	public static void main(String[] args) {
		new LayerLayout();
	}
}
