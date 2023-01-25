package com.game.dungeongame.world.projectiles;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;
import com.game.utils.Vector2f;

public class Projectile extends Entity {

	private Vector2f position;
	private Vector2f destination;
	private Vector2f direction;
	private float speed;

	private CollisionBox box;
	private CollisionBox targetBox;
	private int width, height;

	private Animation animation;
	private boolean isActive;
	
	private Gun shootingGun;//the gun that shot this projectile
	private ProjectileHitListener hitListener;

	public Projectile(String frames) {
		super(0,0);

		animation = new Animation(frames,0.15f);
		animation.setRestartAble(false);
		
		position = new Vector2f(x, y);
		destination = new Vector2f();
		direction = new Vector2f();
		speed = 1;
		width = 32;
		height = 32;

		box = new CollisionBox(x, y, width, height);
		targetBox = new CollisionBox(0,0,0,0);

	}

	@Override
	public void update(float delta) {
		if (isActive) {
			position.add(direction);
			box.move(direction);
			animation.update(delta);
			if (box.overlaps(targetBox)) {
				isActive = false;
				hitListener.hit(this,shootingGun);
			}

		}

	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		if (!isActive)
			return;
		g2.drawImage(animation.getCurrentFrame(), Math.round(position.x - camera.getxOffset()),
				Math.round(position.y - camera.getyOffset()), width, height, null);

	}
	
	public void launch(Vector2f source,CollisionBox targetBox) {
		this.position = source;
		this.box.setLocation(source.x, source.y);
		this.targetBox.set(targetBox);
		
		destination.setLocation(targetBox.x + targetBox.width/2, targetBox.y + targetBox.height/2);
		Vector2f distance =  new Vector2f(destination.x - position.x, destination.y  - position.y);
		direction = new Vector2f(distance);
		direction.normalize();
		direction.scale(speed);
		
		isActive = true;
		animation.reset();
	}
	
	/*	GETTERS AND SETTERS	*/
	
	public void setFrameDelay(float delay) {
		animation.setFrameDelay(delay);
	}
	
	public void setSize(int size) {
		setSize(size,size);
	}
	
	public void setSize(int width,int height) {
		this.width = width;
		this.height = height;
		box.width = width;
		box.height = height;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public CollisionBox getBox() {
		return box;
	}
	
	public CollisionBox getTargetBox() {
		return targetBox;
	}

	public ProjectileHitListener getHitListener() {
		return hitListener;
	}

	public void setHitListener(ProjectileHitListener hitListener) {
		this.hitListener = hitListener;
	}

	public boolean isDead() {
		return !isActive;
	}

	public Gun getShootingGun() {
		return shootingGun;
	}

	public void setShootingGun(Gun shootingGun) {
		this.shootingGun = shootingGun;
	}

}
