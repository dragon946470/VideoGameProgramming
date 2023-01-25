package com.game.spaceshooter.ships;

import java.awt.Graphics2D;

import com.game.spaceshooter.gun.Gun;
import com.game.world.Entity;

public class AIEnemy extends Enemy {

	private Entity entityToFollow;
	private float followSpeed = 1f;
	private Gun gun;

	public AIEnemy(float x, float y) {
		super("enemy3", x, y);

		hp = 15;
		setSpeed(0.5f);
		gun = new Gun("bullet1", 0.25f, 17, 17, 2f, Gun.DOWN_DIRECTION);
	}

	@Override
	public void update(float delta) {
		follow();
		super.update(delta);

		// fire gun
		 gun.fire(position.x + width / 2, position.y + height);
		// update gun
		gun.update(delta);
	}

	private void follow() {
		if (entityToFollow == null)
			return;

		if (position.x < entityToFollow.getX())
			position.x += followSpeed;
		else if (position.x > entityToFollow.getX())
			position.x -= followSpeed;
	}

	@Override
	public void render(Graphics2D g2) {
		super.render(g2);
		gun.render(g2);

	}

	public void follow(Entity entity) {
		this.entityToFollow = entity;
	}

	public void setFollowSpeed(float followSpeed) {
		this.followSpeed = followSpeed;
	}

	@Override
	public Gun getGun() {
		return gun;
	}

}
