package com.game.spaceshooter.ships;

import java.awt.Graphics2D;

import com.game.spaceshooter.gun.Gun;

public class GunEnemy extends Enemy {

	private Gun gun;

	public GunEnemy(float x, float y) {
		super("enemy2", x, y);

		hp = 10;
		setSpeed(0.8f);
		gun = new Gun("bullet2", 0.25f, 17, 17, 2f, Gun.DOWN_DIRECTION);

	}

	@Override
	public void update(float delta) {
		super.update(delta);

		// fire gun
		 gun.fire(position.x + width / 2, position.y + height);
		// update gun
		gun.update(delta);
	}

	@Override
	public void render(Graphics2D g2) {
		super.render(g2);
		gun.render(g2);

	}

	@Override
	public Gun getGun() {
		return gun;
	}

}
