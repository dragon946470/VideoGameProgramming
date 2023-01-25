package com.game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {
	private boolean keys[];
	private boolean keysJustPressed[], keysPressable[];

	public KeyManager() {
		keys = new boolean[KeyEvent.RESERVED_ID_MAX];
		keysJustPressed = new boolean[KeyEvent.RESERVED_ID_MAX];
		keysPressable = new boolean[KeyEvent.RESERVED_ID_MAX];
	}

	public void update(float delta) {
		for (int i = 0; i < keys.length; i++) {
			if (!keysPressable[i] && !keys[i]) {
				keysPressable[i] = true;
			} else if (keysJustPressed[i]) {
				keysJustPressed[i] = false;
				keysPressable[i] = false;
			}
			if (keysPressable[i] && keys[i]) {
				keysJustPressed[i] = true;
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode];
	}

	public boolean isKeyJustPressed(int keyCode) {
		return keysJustPressed[keyCode];
	}

}
