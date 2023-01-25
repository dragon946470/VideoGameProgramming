package com.game.dungeongame.world.projectiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;
import com.game.utils.Vector2f;

public class Gun extends Entity {

	private ArrayList<Projectile> projectiles;
	private float fireDelay = 1f;
	private float fireDelayCount;
	private boolean canFire;
	private int maxProjectiles;

	private int projectileSize;
	private float projectileSpeed;
	private float frameDelay;

	private float hitDamage;
	
	private ProjectileHitListener hitListener;
	private String frames;

	public Gun( String frames, float frameDelay, float projectileSpeed,
			int projectileSize, int maxProjectiles) {
		super(0, 0);
		this.frames = frames;
		this.frameDelay = frameDelay;
		this.projectileSpeed = projectileSpeed;
		this.projectileSize = projectileSize;
		this.maxProjectiles = maxProjectiles;

		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update(delta);

		for (int i = projectiles.size() - 1; i >= 0; i--)
			if (projectiles.get(i).isDead())
				projectiles.remove(i);

		if (!canFire) {
			fireDelayCount += delta;
			if (fireDelayCount >= fireDelay) {
				fireDelayCount = 0;
				canFire = true;
			}

		}

	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(g2, camera);
	}

	public void fire(float x, float y, Vector2f destination) {

		

		Projectile p = new Projectile(frames);
		p.setSize(projectileSize);
		p.setFrameDelay(frameDelay);
		p.setSpeed(projectileSpeed);
		p.setHitListener(hitListener);
		p.setShootingGun(this);

		p.launch(new Vector2f(x, y), new CollisionBox(destination.x, destination.y, projectileSpeed, projectileSpeed));
		projectiles.add(p);
		canFire = false;
	}
	
	public boolean canFire() {
		return projectiles.size() < maxProjectiles && canFire;
			
	}
	
	public void setFireDelay(float delay) {
		this.fireDelay = delay;
	}
	
	public void setHitListener(ProjectileHitListener hitListener) {
		this.hitListener = hitListener;
	}

	public float getHitDamage() {
		return hitDamage;
	}

	public void setHitDamage(float hitDamage) {
		this.hitDamage = hitDamage;
	}

}
