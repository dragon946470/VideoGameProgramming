package com.game.spaceshooter.ships;

import java.awt.Graphics2D;

import com.game.spaceshooter.Game;
import com.game.spaceshooter.gun.Gun;
import com.game.utils.Vector2f;

public abstract class Enemy extends SpaceShip {

	protected Vector2f velocity;
	protected float speed = 1;
	
	public Enemy(String animationKey, float x, float y) {
		super(animationKey, x, y);

		velocity = new Vector2f(0, 1);

	}

	@Override
	public void update(float delta) {
		// move enemy
		position.add(velocity);

		// update collision box location
		box.x = position.x;
		box.y = position.y;

		// update animation
		currentAnimation.update(delta);

		// if out of screen, then it dies
		if (position.y > Game.getGameHeight())
			isDead = true;

	}

	@Override
	public void render(Graphics2D g2) {
		super.render(g2);

		if (drawDebug) {
			box.render(g2);
		}
	}


	public void setSpeed(float speed) {
		this.speed = speed;
		velocity.scale(speed);
	}

	public abstract Gun getGun();

	

}
