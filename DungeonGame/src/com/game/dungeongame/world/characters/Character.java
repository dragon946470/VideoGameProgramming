package com.game.dungeongame.world.characters;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;

public abstract class Character extends Entity {

	public static final int FACE_RIGHT = 0;
	public static final int FACE_LEFT = 1;
	public static final int FACE_DOWN = 2;
	public static final int FACE_UP = 3;

	protected Animation moveAnimation[];
	protected Animation currentAnimation;

	protected TileMap tileMap;
	protected CollisionBox box;
	protected int width, height;

	protected int face;
	protected boolean up, down, right, left;
	protected float speed;

	protected float hp;
	protected boolean isDead;

	protected boolean drawDebug;

	public Character(TileMap tileMap, float x, float y) {
		super(x, y);
		this.tileMap = tileMap;
	}

	@Override
	public void update(float delta) {
		currentAnimation.update(delta);
		move();
		updateImageLocation();
	}

	public void render(Graphics2D g2, Camera camera) {
		g2.drawImage(currentAnimation.getCurrentFrame(), Math.round(x - camera.getxOffset()),
				Math.round(y - camera.getyOffset()), width, height, null);
	}

	protected void updateImageLocation() {
		x = box.x;
		y = box.y;
	}

	protected void move() {
		if (up) {
			if (!isUpCollision(speed))
				box.y -= speed;

			face = FACE_UP;
		}
		if (down) {
			if (!isDownCollision(speed))
				box.y += speed;

			face = FACE_DOWN;
		}
		if (left) {
			if (!isLeftCollision(speed))
				box.x -= speed;

			face = FACE_LEFT;
		}
		if (right) {
			if (!isRightCollision(speed))
				box.x += speed;

			face = FACE_RIGHT;
		}
	}

	protected void resetMovement() {
		up = false;
		down = false;
		right = false;
		left = false;
	}

	protected boolean isUpCollision(float dy) {
		return tileMap.isSolidTile(box.x, box.y - dy) || tileMap.isSolidTile(box.x + box.width, box.y - dy);
	}

	protected boolean isRightCollision(float dx) {
		return tileMap.isSolidTile(box.x + box.width + dx, box.y + box.height)
				|| tileMap.isSolidTile(box.x + box.width + dx, box.y);
	}

	protected boolean isDownCollision(float dy) {
		return tileMap.isSolidTile(box.x, box.y + box.height + dy)
				|| tileMap.isSolidTile(box.x + box.width, box.y + box.height + dy);
	}

	protected boolean isLeftCollision(float dx) {
		return tileMap.isSolidTile(box.x - dx, box.y) || tileMap.isSolidTile(box.x - dx, box.y + box.height);
	}

	public void hurt(float dmg) {
		hp -= dmg;
		if (hp <= 0) {
			hp = 0;
			isDead = true;
		}
	}

	// GETTERS

	public void setDrawDebug(boolean drawDebug) {
		this.drawDebug = drawDebug;
	}

	public CollisionBox getBox() {
		return box;
	}

	public boolean isDead() {
		return isDead;
	}

	public float getHp() {
		return hp;
	}
}
