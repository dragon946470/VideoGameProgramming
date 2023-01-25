package com.game.spaceshooter.ships;

import com.game.spaceshooter.gun.Gun;

public class SimpleEnemy extends Enemy{

	public SimpleEnemy(float x, float y) {
		super("enemy1", x, y);
		hp = 5;

	}

	@Override
	public Gun getGun() {
		return null;
	}

}
