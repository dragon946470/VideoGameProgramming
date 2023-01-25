package com.game.spaceshooter.gun;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.game.utils.Vector2f;
import com.game.world.Entity;

public class Gun extends Entity {

	public static final int UP_DIRECTION = 1;
	public static final int DOWN_DIRECTION = 2;

	private ArrayList<Bullet> bullets;

	private Vector2f velocity;
	private int bulletWidth, bulletHeight;
	private String animationKey;
	private float animationDelay;

	private float fireCoolDown = 2f;
	private float fireCoolDownCount;
	private boolean canFire;

	public Gun(String animationKey, float animationDelay, int bulletWidth, int bulletHeight, float speed,
			int direction) {
		super(0, 0);

		bullets = new ArrayList<Bullet>();

		this.animationKey = animationKey;
		this.animationDelay = animationDelay;
		this.bulletWidth = bulletWidth;
		this.bulletHeight = bulletHeight;

		velocity = direction == UP_DIRECTION ? new Vector2f(0, -speed) : new Vector2f(0, speed);

	}

	@Override
	public void update(float delta) {
		for (Bullet bullet : bullets)
			bullet.update(delta);

		for (int i = bullets.size() - 1; i >= 0; i--)
			if (bullets.get(i).isDead())
				bullets.remove(i);

		fireCoolDownCount += delta;
		if (fireCoolDownCount >= fireCoolDown) {
			fireCoolDownCount = 0;
			canFire = true;
		}
	}

	@Override
	public void render(Graphics2D g2) {
		for (Bullet bullet : bullets)
			bullet.render(g2);
	}

	public boolean fire(float x, float y) {
		if (!canFire)
			return false;

		Bullet bullet = new Bullet(x, y, velocity, animationKey, animationDelay, bulletWidth, bulletHeight);
		bullets.add(bullet);
		canFire = false;
		
		return true;
	}
	
	public void setFireCoolDown(float fireCoolDown) {
		this.fireCoolDown = fireCoolDown;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
