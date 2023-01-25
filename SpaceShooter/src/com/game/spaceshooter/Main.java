package com.game.spaceshooter;

public class Main {

	public static void main(String[] args) {
		GameConfigurations gc = new GameConfigurations();
		gc.setTitle("Space Shooter");
		gc.setFps(60);
		gc.setFullScreen(false);
		gc.setGameHeight(480);
		gc.setGameWidth(600);
		// gc.setScaling(true);
		// gc.setResizeable(true);

		SpaceShooterGame game = new SpaceShooterGame();
		Launcher.launch(gc, game);
	}

}
