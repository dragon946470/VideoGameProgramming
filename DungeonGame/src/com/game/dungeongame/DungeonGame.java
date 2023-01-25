package com.game.dungeongame;

import com.game.dungeoncrawler.states.GameEndState;
import com.game.dungeoncrawler.states.GameState;
import com.game.dungeoncrawler.states.MenuState;
import com.game.dungeoncrawler.states.State;
import com.game.gfx.AssetManager;

public class DungeonGame extends Game {

	private GameState gameState;
	private GameEndState gameEndState;
	private MenuState menuState;
	private State state;

	@Override
	public void create() {
		AssetManager.load();
		gameEndState = new GameEndState(this);
		menuState = new MenuState(this);
		setState(menuState);
	}

	@Override
	public void update(float delta) {
		state.update(delta);

	}

	@Override
	public void render() {
		state.render(graphics);
		//renderfpsCount(Color.ORANGE);

	}

	public void nextLevel(GameState source) {
			setState(gameEndState);
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setMenuState() {
		setState(menuState);
	}

	public void startGame() {
		gameState = new GameState(this);
		setState(gameState);
	}

	public void restartLevel(GameState gameState) {
		gameState = new GameState(this);
		setState(gameState);

	}

}
