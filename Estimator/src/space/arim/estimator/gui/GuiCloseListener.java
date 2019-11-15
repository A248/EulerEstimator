package space.arim.estimator.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiCloseListener extends WindowAdapter {
	private final JFrame frame;
	public GuiCloseListener(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void windowClosing(WindowEvent evt) {
		if (JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to quit?",
				"Exit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			frame.dispose();
		} else {
			frame.setVisible(true);
		}
	}
}
