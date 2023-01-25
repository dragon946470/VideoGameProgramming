package com.game.states;

import java.awt.Graphics2D;

import com.game.spaceshooter.SpaceShooterGame;

public abstract class State {

	
	protected SpaceShooterGame game;
	
	public State(SpaceShooterGame game) {
		this.game = game;
	}
	
	public abstract void update(float delta);
	public abstract void render(Graphics2D g2);
	
	public SpaceShooterGame getGame() {
		return game;
	}
}
