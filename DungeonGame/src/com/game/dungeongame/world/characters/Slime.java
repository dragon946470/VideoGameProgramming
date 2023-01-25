package com.game.dungeongame.world.characters;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;

public class Slime extends Character {

	private Character characterToFollow;
	private CollisionBox radarBox;

	public Slime(TileMap tileMap, float x, float y) {
		super(tileMap, x, y);

		width = 32;
		height = 32;
		speed = 0.5f;
		face = FACE_RIGHT;
		hp = 5;

		moveAnimation = new Animation[4];
		moveAnimation[FACE_LEFT] = new Animation("slime_walk_left", 0.25f);
		moveAnimation[FACE_RIGHT] = new Animation("slime_walk_right", 0.25f);
		moveAnimation[FACE_DOWN] = new Animation("slime_walk_down", 0.25f);
		moveAnimation[FACE_UP] = new Animation("slime_walk_up", 0.25f);
		currentAnimation = moveAnimation[face];

		box = new CollisionBox(x, y, width, height);
		radarBox = new CollisionBox(x - (width * 2), y - (height * 2), width * 5, height * 5);

	}

	@Override
	public void update(float delta) {
		resetMovement();
		follow();
		super.update(delta);
		updateRadarBoxLocation();

		currentAnimation = moveAnimation[face];
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		super.render(g2, camera);

		if (drawDebug) {
			box.render(g2, camera);
			radarBox.render(g2, camera);
		}
	}

	public void follow(Character e) {
		if (radarBox.overlaps(e.getBox()))
			characterToFollow = e;
	}

	private void updateRadarBoxLocation() {
		radarBox.setLocation(x - width * 2, y - height * 2);
	}

	private void follow() {
		if (characterToFollow == null)
			return;

		if (characterToFollow.getX() < x)
			left = true;
		else if (characterToFollow.getX() > x)
			right = true;

		if (characterToFollow.getY() < y)
			up = true;
		else if (characterToFollow.getY() > y)
			down = true;
	}

}
