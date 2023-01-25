package com.game.spaceshooter.ships;

import java.awt.Graphics2D;

import com.game.spaceshooter.Game;
import com.game.spaceshooter.gun.Gun;

public class Player extends SpaceShip {

	private Gun gun;

	public Player(float x, float y) {
		super("playerShip", x, y);

		hp = 20;
		gun = new Gun("playerBullet", 0.25f, 10, 20, 2f, Gun.UP_DIRECTION);
		gun.setFireCoolDown(0.5f);
	}

	@Override
	public void update(float delta) {
		if (isDead)
			return;

		currentAnimation.update(delta);

		// set position
		position.x = Game.mouseManager.getX() - width;
		position.y = Game.mouseManager.getY() - height;

		// update box position
		box.x = position.x;
		box.y = position.y;

		// fire gun
		if (Game.mouseManager.isLeftPressed()) {
			gun.fire(position.x, position.y);
		}

		// update gun
		gun.update(delta);

	}

	@Override
	public void render(Graphics2D g2) {
		if (isDead)
			return;

		super.render(g2);
		gun.render(g2);

		if (drawDebug) {
			box.render(g2);
		}
	}

	public Gun getGun() {
		return gun;
	}

}
