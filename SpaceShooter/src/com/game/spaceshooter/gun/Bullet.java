package com.game.spaceshooter.gun;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.spaceshooter.Game;
import com.game.utils.Vector2f;
import com.game.world.CollisionBox;
import com.game.world.Entity;

public class Bullet extends Entity {

	private Animation animation;
	private Vector2f velocity;

	private CollisionBox box;
	private int width, height;
	private boolean isDead;

	public Bullet(float x, float y, Vector2f velocity, String animationKey, float frameDelay, int width, int height) {
		super(x, y);

		this.velocity = velocity;
		this.animation = new Animation(animationKey, frameDelay);
		animation.setIsRestartable(false);
		this.width = width;
		this.height = height;

		box = new CollisionBox(x, y, width, height);
		box.setColor(Color.RED);
	}

	@Override
	public void update(float delta) {
		animation.update(delta);
		position.add(velocity);

		// update collision box
		box.x = position.x;
		box.y = position.y;

		if (position.y + height < 0 || position.y > Game.getGameHeight())
			isDead = true;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(animation.getCurrentFrame(), (int) position.x, (int) position.y, width, height, null);
		
		//box.render(g2);
	}

	public boolean isDead() {
		return isDead;
	}

	public CollisionBox getBox() {
		return box;
	}

	public void die() {
		isDead = true;
		
	}

}
