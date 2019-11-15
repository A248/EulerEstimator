package space.arim.estimator.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiMouseListener implements MouseListener {

	private final UserFrame frame;
	
	public GuiMouseListener(UserFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		frame.execute();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
