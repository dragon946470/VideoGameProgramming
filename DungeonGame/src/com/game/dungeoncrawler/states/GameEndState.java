package com.game.dungeoncrawler.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.dungeongame.DungeonGame;
import com.game.dungeongame.Game;
import com.game.utils.FontUtils;

public class GameEndState extends State {

	private Font font = new Font("Monospace", Font.PLAIN, 40);

	private float blinkDelayCount;
	private float blinkDelay = 1f;
	private boolean blink = true;

	public GameEndState(DungeonGame game) {
		super(game);

	}

	@Override
	public void update(float delta) {

		blinkDelayCount += delta;
		if (blinkDelayCount >= blinkDelay) {
			blinkDelayCount = 0;
			blink = !blink;
		}

		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_SPACE)) {
			blinkDelayCount = 0;
			blink = false;
			game.setMenuState();
		}

	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());

		String level = "Game Completed";
		g2.setFont(font);
		g2.setColor(Color.WHITE);

		float x = (Game.getGameWidth() - FontUtils.getTextWidth(font, level)) / 2;
		float y = (Game.getGameHeight() - font.getSize()) / 2;
		g2.drawString(level, x, y);

		if (blink)
			return;
		String msg = "Press space to continue";
		x = (Game.getGameWidth() - FontUtils.getTextWidth(font, msg)) / 2;
		y = y +  font.getSize() * 3;
		g2.drawString(msg, x, y);
	}

}
