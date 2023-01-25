package com.game.dungeongame;

public class Main {

	public static void main(String[] args) {
		GameConfigurations gc = new GameConfigurations();
		gc.setFps(60);
		gc.setFullScreen(false);
		
		gc.setGameHeight(480);
		gc.setGameWidth(672);
		
		gc.setScaling(false);
		gc.setResizeable(false);
		
		gc.setTitle("Dungeon Game");
		
		DungeonGame game = new DungeonGame();		
		Launcher.launch(gc, game);
	}

}
