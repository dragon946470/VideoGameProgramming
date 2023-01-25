package com.game.dungeoncrawler.states;

import java.awt.Graphics2D;

import com.game.dungeongame.DungeonGame;
public abstract class State {

	
	protected DungeonGame game;
	
	public State(DungeonGame game) {
		this.game = game;
	}
	
	public abstract void update(float delta);
	public abstract void render(Graphics2D g2);
	
	public DungeonGame getGame() {
		return game;
	}
}
