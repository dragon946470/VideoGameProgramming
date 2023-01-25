package com.game.dungeongame.world.characters;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;
import com.game.dungeongame.world.projectiles.Gun;
import com.game.dungeongame.world.projectiles.ProjectileHitListener;
import com.game.utils.Vector2f;

public class Boss extends Character {

	private Animation[] idleAnimation;

	private float moveDelay = 1f;
	private float moveDelayCount;

	private boolean isMoving;
	private float pauseDelay = 3f;
	private float pauseDelayCount;

	private CollisionBox radarBox;
	private Gun gun;

	public Boss(TileMap tileMap, float x, float y) {
		super(tileMap, x, y);

		width = 96;
		height = 96;
		speed = 0.5f;
		face = FACE_RIGHT;
		hp = 20;

		idleAnimation = new Animation[4];
		idleAnimation[FACE_LEFT] = new Animation("boss_idle_left", 0.15f);
		idleAnimation[FACE_RIGHT] = new Animation("boss_idle_right", 0.15f);
		idleAnimation[FACE_DOWN] = new Animation("boss_idle_left", 0.15f);
		idleAnimation[FACE_UP] = new Animation("boss_idle_right", 0.15f);

		moveAnimation = new Animation[4];
		moveAnimation[FACE_LEFT] = new Animation("boss_move_left", 0.15f);
		moveAnimation[FACE_RIGHT] = new Animation("boss_move_right", 0.15f);
		moveAnimation[FACE_DOWN] = new Animation("boss_move_left", 0.15f);
		moveAnimation[FACE_UP] = new Animation("boss_move_right", 0.15f);
		currentAnimation = idleAnimation[face];

		box = new CollisionBox(x, y + height / 2, width, height / 2);
		radarBox = new CollisionBox(x - width * 3, y - height * 3, width * 7, height * 7);

		gun = new Gun("projectile_4", 0.2f, 3f, 24, 1);
		gun.setHitDamage(2f);
		gun.setFireDelay(2f);
	}

	@Override
	public void update(float delta) {
		if (isDead)
			return;

		if (isMoving) {
			moveDelayCount += delta;
			if (moveDelayCount >= moveDelay) {
				moveDelayCount = 0;
				isMoving = false;
				resetMovement();
				currentAnimation = idleAnimation[face];
			}
		} else {
			pauseDelayCount += delta;
			if (pauseDelayCount >= pauseDelay) {
				pauseDelayCount = 0;
				isMoving = true;
				changeDirection();
			}
		}

		move();
		currentAnimation.update(delta);
		currentAnimation = moveAnimation[face];

		x = box.x;
		y = box.y - box.height;
		updateRadarBoxLocation();

		gun.update(delta);
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		if (isDead)
			return;
		super.render(g2, camera);
		gun.render(g2, camera);

		if (drawDebug) {
			box.render(g2, camera);
			radarBox.render(g2, camera);
		}
	}

	public void fire(Vector2f destination) {
		if (gun.canFire()) {
			gun.fire(x, y, destination);
			gun.fire(x, y, destination);
		}
	}

	private void changeDirection() {
		if (face == FACE_RIGHT) {
			left = true;
			right = false;

			face = FACE_LEFT;
		} else {
			right = true;
			left = false;

			face = FACE_RIGHT;
		}
	}

	private void updateRadarBoxLocation() {
		radarBox.setLocation(x - width * 3, y - height * 3);
	}

	public CollisionBox getRadarBox() {
		return radarBox;
	}

	public void setHitListener(ProjectileHitListener hitListener) {
		gun.setHitListener(hitListener);
	}

}
