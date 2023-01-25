package com.game.spaceshooter;

public final class Launcher {

	public static final void launch(GameConfigurations config,Game game) {
		game.initialize(config);
		game.start();
	}
}
