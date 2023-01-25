package com.game.spaceshooter.ships;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.world.CollisionBox;
import com.game.world.Entity;

public abstract class SpaceShip extends Entity {

	protected Animation currentAnimation;
	protected int width, height;
	protected CollisionBox box;
	protected float hp;
	protected boolean isDead;
	protected boolean drawDebug;

	public SpaceShip(String animationKey,float x, float y) {
		super(x, y);
		
		currentAnimation = new Animation(animationKey, 0.15f);
		width = (int) (currentAnimation.getCurrentFrame().getWidth() * 1.5);
		height = (int) (currentAnimation.getCurrentFrame().getHeight() * 1.5);

		box = new CollisionBox(x, y, width, height);
		box.setColor(Color.YELLOW);

	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(currentAnimation.getCurrentFrame(), (int) position.x, (int) position.y, width, height, null);
	}
	

	public CollisionBox getBox() {
		return box;
	}
	

	public boolean isDead() {
		return isDead;
	}
	
	public void hurt(float damage) {
		hp -= damage;
		if(hp <= 0) {
			hp = 0;
			isDead = true;
		}
	}
	
	public float getHp() {
		return hp;
	}

}
