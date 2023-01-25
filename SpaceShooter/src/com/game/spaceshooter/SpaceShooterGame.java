package com.game.spaceshooter;

import com.game.gfx.AssetManager;
import com.game.states.GameState;
import com.game.states.MenuState;
import com.game.states.State;

public class SpaceShooterGame extends Game {

	private GameState gameState;
	private MenuState menuState;
	private State state;

	@Override
	public void create() {
		AssetManager.load();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		showMenu();

	}

	@Override
	public void update(float delta) {
		state.update(delta);

	}

	@Override
	public void render() {
		state.render(graphics);

	}

	public void startGame() {
		gameState.reset();
		state = gameState;
	}

	public void showMenu() {
		state = menuState;
	}

}
