package com.game.dungeoncrawler.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.game.dungeongame.DungeonGame;
import com.game.dungeongame.Game;
import com.game.utils.FontUtils;

public class LevelTransitionState extends State {

	private GameState nextLevelState;
	private int levelNo;
	
	private float waitTimer;
	private float waitTime = 2.5f;
	
	private Font font = new Font("Monospace",Font.PLAIN,40);

	public LevelTransitionState(DungeonGame game) {
		super(game);

	}

	@Override
	public void update(float delta) {
		waitTimer += delta;
		
		if(waitTimer >= waitTime) {
			waitTimer = 0;
			game.setState(nextLevelState);
		}
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());

		String level = "Level " + levelNo;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString(level, (Game.getGameWidth() - FontUtils.getTextWidth(font, level))/2,
				(Game.getGameHeight() - font.getSize())/2);

	}

	public void startTransition(GameState nextLevelState, int levelNo) {
		this.nextLevelState = nextLevelState;
		this.levelNo = levelNo;
	}

}
