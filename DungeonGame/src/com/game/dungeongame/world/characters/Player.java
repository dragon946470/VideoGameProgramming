package com.game.dungeongame.world.characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.animation.Animation;
import com.game.dungeongame.Game;
import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;
import com.game.dungeongame.world.projectiles.Gun;
import com.game.dungeongame.world.projectiles.ProjectileHitListener;
import com.game.utils.Vector2f;

public class Player extends Character {

	private Animation attackAnimation[];
	private CollisionBox attackBoxes[];
	private boolean isAttacking;

	private Gun gun;
	private Gun specialAbility;
	private Vector2f shootLocation;
	private Vector2f abilityAttackLocation;
	private boolean shoot;
	private boolean shootAbility;

	private float maxHp = 30;
	private int coins;

	public Player(TileMap tileMap, float x, float y) {
		super(tileMap, x, y);

		width = 48;
		height = 48;
		speed = 2f;
		face = FACE_RIGHT;
		hp = maxHp;

		// initialize animations
		moveAnimation = new Animation[4];
		moveAnimation[FACE_LEFT] = new Animation("walk_left", 0.1f);
		moveAnimation[FACE_RIGHT] = new Animation("walk_right", 0.1f);
		moveAnimation[FACE_DOWN] = new Animation("walk_down", 0.1f);
		moveAnimation[FACE_UP] = new Animation("walk_up", 0.1f);

		attackAnimation = new Animation[4];
		attackAnimation[FACE_LEFT] = new Animation("attack_left", 0.1f);
		attackAnimation[FACE_RIGHT] = new Animation("attack_right", 0.1f);
		attackAnimation[FACE_DOWN] = new Animation("attack_down", 0.1f);
		attackAnimation[FACE_UP] = new Animation("attack_up", 0.1f);
		currentAnimation = moveAnimation[FACE_RIGHT];

		// initalize collision boxes
		box = new CollisionBox(x + width / 2, y + width / 2, width / 2, height / 2);
		attackBoxes = new CollisionBox[4];
		attackBoxes[FACE_LEFT] = new CollisionBox(box.x, box.y, box.width / 2, box.height);
		attackBoxes[FACE_RIGHT] = new CollisionBox(box.x + box.width / 2, box.y, box.width / 2, box.height);
		attackBoxes[FACE_DOWN] = new CollisionBox(box.x, box.y + box.height / 2, box.width, box.height / 2);
		attackBoxes[FACE_UP] = new CollisionBox(box.x, box.y, box.width, box.height / 2);

		attackBoxes[FACE_LEFT].setColor(Color.RED);
		attackBoxes[FACE_RIGHT].setColor(Color.BLUE);
		attackBoxes[FACE_DOWN].setColor(Color.GREEN);
		attackBoxes[FACE_UP].setColor(Color.YELLOW);

		// initialize gun
		gun = new Gun("projectile_3", 0.2f, 3.5f, 32, 2);
		gun.setHitDamage(0.8f);
		shootLocation = new Vector2f();

		// initialize specialAbility
		specialAbility = new Gun("projectile_2", 0.1f, 3.5f, 48, 1);
		specialAbility.setHitDamage(2f);
		abilityAttackLocation = new Vector2f();
	}

	@Override
	public void update(float delta) {
		currentAnimation.update(delta);

		// movement
		input();
		if (up) {
			if (!isUpCollision(speed)) {
				box.y -= speed;
				moveAttackBoxes(0, -speed);
			}
			face = FACE_UP;
		}
		if (down) {
			if (!isDownCollision(speed)) {
				box.y += speed;
				moveAttackBoxes(0, speed);
			}
			face = FACE_DOWN;
		}
		if (left) {
			if (!isLeftCollision(speed)) {
				box.x -= speed;
				moveAttackBoxes(-speed, 0);
			}
			face = FACE_LEFT;
		}
		if (right) {
			if (!isRightCollision(speed)) {
				box.x += speed;
				moveAttackBoxes(speed, 0);
			}
			face = FACE_RIGHT;
		}

		// animation
		if (isAttacking) {
			currentAnimation = attackAnimation[face];
		} else if (up || left || right || down)
			currentAnimation = moveAnimation[face];
		else {
			resetAnimations();
			currentAnimation = moveAnimation[face];

		}

		// attacking
		if (isAttacking) {
			if (currentAnimation.isComplete()) {
				isAttacking = false;
				currentAnimation.reset();
			}

		}

		// shooting
		gun.update(delta);
		if (shoot) {
			if (gun.canFire())
				gun.fire(box.x, box.y, shootLocation);
			shoot = false;
		}

		// special ability
		specialAbility.update(delta);
		if (shootAbility) {
			if (specialAbility.canFire())
				specialAbility.fire(box.x, box.y, abilityAttackLocation);
			shootAbility = false;
		}

		// update image location
		x = box.x - width / 4;
		y = box.y - height / 4;

	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		super.render(g2, camera);
		gun.render(g2, camera);
		specialAbility.render(g2, camera);

		if (drawDebug) {
			box.render(g2, camera);
			attackBoxes[FACE_LEFT].render(g2, camera);
			attackBoxes[FACE_RIGHT].render(g2, camera);
			attackBoxes[FACE_DOWN].render(g2, camera);
			attackBoxes[FACE_UP].render(g2, camera);
		}

	}

	private void resetAnimations() {
		for (int i = 0; i < moveAnimation.length; i++)
			moveAnimation[i].reset();
		for (int i = 0; i < attackAnimation.length; i++)
			attackAnimation[i].reset();

	}

	private void moveAttackBoxes(float dx, float dy) {
		for (int i = 0; i < attackBoxes.length; i++)
			attackBoxes[i].move(dx, dy);
	}

	private void input() {
		up = false;
		left = false;
		right = false;
		down = false;

		if (Game.keyManager.isKeyPressed(KeyEvent.VK_W))
			up = true;
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_A))
			left = true;
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_D))
			right = true;
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_S))
			down = true;
		if (Game.keyManager.isKeyPressed(KeyEvent.VK_SPACE))
			isAttacking = true;

		if (Game.mouseManager.isLeftPressed()) {
			shoot = true;
			shootLocation.x = Game.mouseManager.getX();
			shootLocation.y = Game.mouseManager.getY();
		}
		if (Game.mouseManager.isRightPressed()) {
			shootAbility = true;
			abilityAttackLocation.x = Game.mouseManager.getX();
			abilityAttackLocation.y = Game.mouseManager.getY();
		}

	}

	public void hpUp(float up) {
		hp += up;
		if (hp > maxHp)
			hp = maxHp;
	}

	public void coinPicked() {
		coins++;
	}

	// GETTERS AND SETTERS

	@Override
	public void setLocation(float x, float y) {
		super.setLocation(x, y);
		box.setLocation(x + width / 2, y + width / 2);

		attackBoxes[FACE_LEFT].setLocation(box.x, box.y);
		attackBoxes[FACE_RIGHT].setLocation(box.x + box.width / 2, box.y);
		attackBoxes[FACE_DOWN].setLocation(box.x, box.y + box.height / 2);
		attackBoxes[FACE_UP].setLocation(box.x, box.y);
	}

	public int getCoins() {
		return coins;
	}

	public CollisionBox getAttackBox() {
		return attackBoxes[face];
	}

	public void setGunHitListener(ProjectileHitListener hitListener) {
		gun.setHitListener(hitListener);
		specialAbility.setHitListener(hitListener);
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;

	}

	public Gun getGun() {
		return gun;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public Gun getSpecialAbility() {
		return specialAbility;
	}

}
