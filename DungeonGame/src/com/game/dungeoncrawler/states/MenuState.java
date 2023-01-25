package com.game.dungeoncrawler.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.dungeongame.DungeonGame;
import com.game.dungeongame.Game;
import com.game.utils.FontUtils;

public class MenuState extends State {

	private static final int START_GAME = 1;
	private static final int QUIT_GAME = 2;

	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private Font font = new Font("Arial", Font.PLAIN, 40);

	private int optionSelected = START_GAME;

	public MenuState(DungeonGame game) {
		super(game);

	}

	@Override
	public void update(float delta) {
		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_ENTER)) {

			switch (optionSelected) {
			case START_GAME:
				game.startGame();
				break;
			case QUIT_GAME:
				System.exit(0);
				break;
			}
		}
		
		if(Game.keyManager.isKeyPressed(KeyEvent.VK_S))
			optionSelected = QUIT_GAME;
		if(Game.keyManager.isKeyPressed(KeyEvent.VK_W))
			optionSelected = START_GAME;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());

		g2.setColor(Color.WHITE);

		// draw title
		g2.setFont(titleFont);
		String title = "DUNGEON GAME";
		float x = (Game.getWidth() - FontUtils.getTextWidth(titleFont, title)) / 2;
		g2.drawString(title, x, titleFont.getSize() + 20);

		// draw options
		g2.setFont(font);
		String start = "Start";
		float x1 = (Game.getWidth() - FontUtils.getTextWidth(font, start)) / 2;
		g2.drawString(start, x1, titleFont.getSize() + 80);
		String quit = "Quit";
		float x2 = (Game.getWidth() - FontUtils.getTextWidth(font, quit)) / 2;
		g2.drawString(quit, x2, titleFont.getSize() + font.getSize() + 100);

		// draw select btn
		int x3 = (int) (optionSelected == START_GAME ? x1 - 30 : x2 - 30);
		int y = optionSelected == START_GAME ? titleFont.getSize() + 55 : titleFont.getSize() + font.getSize() + 75;
		g2.fillOval(x3, y, 20, 20);

	}

}
