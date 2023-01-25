package com.game.dungeongame.world.characters;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;
import com.game.dungeongame.world.projectiles.Gun;
import com.game.dungeongame.world.projectiles.ProjectileHitListener;
import com.game.utils.Vector2f;

public class Bat extends Character {

	private float moveDelay = 1f;
	private float moveDelayCount;

	private boolean isMoving;
	private float pauseDelay = 1f;
	private float pauseDelayCount;

	private CollisionBox radarBox;
	private Gun gun;

	public Bat(TileMap tileMap, float x, float y) {
		super(tileMap, x, y);

		width = 32;
		height = 32;
		speed = 0.5f;
		face = FACE_RIGHT;
		hp = 8;

		moveAnimation = new Animation[4];
		moveAnimation[FACE_LEFT] = new Animation("bat_move_left", 0.15f);
		moveAnimation[FACE_RIGHT] = new Animation("bat_move_right", 0.15f);
		moveAnimation[FACE_DOWN] = new Animation("bat_move_left", 0.15f);
		moveAnimation[FACE_UP] = new Animation("bat_move_right", 0.15f);
		currentAnimation = moveAnimation[face];

		box = new CollisionBox(x, y, width, height);
		radarBox = new CollisionBox(x - width * 4, y - width * 4, width * 9, height * 9);

		gun = new Gun("projectile_1", 0.2f, 3f, 24, 1);
		gun.setHitDamage(0.8f);
		gun.setFireDelay(2f);
	}

	@Override
	public void update(float delta) {

		if (isMoving) {
			moveDelayCount += delta;
			if (moveDelayCount >= moveDelay) {
				moveDelayCount = 0;
				isMoving = false;
				resetMovement();
			}
		} else {
			pauseDelayCount += delta;
			if (pauseDelayCount >= pauseDelay) {
				pauseDelayCount = 0;
				isMoving = true;
				changeDirection();
			}
		}

		super.update(delta);
		currentAnimation = moveAnimation[face];
		updateRadarBoxLocation();

		gun.update(delta);
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		super.render(g2, camera);
		gun.render(g2, camera);
		
		if(drawDebug) {
			box.render(g2, camera);
			radarBox.render(g2, camera);
		}
	}

	public void fire(Vector2f destination) {
		if (gun.canFire())
			gun.fire(x, y, destination);
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
		radarBox.setLocation(x - width * 4, y - height * 4);
	}

	public CollisionBox getRadarBox() {
		return radarBox;
	}

	public void setHitListener(ProjectileHitListener hitListener) {
		gun.setHitListener(hitListener);
	}

}
