package com.game.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter {

	private boolean leftPress, rightPress;
	private float x, y;

	public boolean isLeftPressed() {
		return leftPress;
	}

	public boolean isRightPressed() {
		return rightPress;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPress = true;
		if (e.getButton() == MouseEvent.BUTTON2)
			rightPress = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPress = false;
		if (e.getButton() == MouseEvent.BUTTON2)
			rightPress = false;

	}

}
