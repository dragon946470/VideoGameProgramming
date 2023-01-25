package com.game.dungeoncrawler.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.dungeongame.DungeonGame;
import com.game.dungeongame.Game;
import com.game.dungeongame.world.World;
import com.game.dungeongame.world.WorldLoader;
import com.game.dungeongame.world.characters.Bat;
import com.game.dungeongame.world.characters.Player;
import com.game.dungeongame.world.characters.Slime;
import com.game.dungeongame.world.objects.Door;
import com.game.dungeongame.world.objects.PickupObject;
import com.game.dungeongame.world.projectiles.Gun;
import com.game.dungeongame.world.projectiles.Projectile;
import com.game.dungeongame.world.projectiles.ProjectileHitListener;
import com.game.utils.FontUtils;
import com.game.utils.Vector2f;

public class GameState extends State implements ProjectileHitListener {

	private World world;
	private Player player;

	private String lvlName;
	private boolean isLevelEnded;

	private boolean isPaused;

	private Font font = new Font("arial", Font.BOLD, 15);
	private Font pauseFont = new Font("Monospace", Font.BOLD, 30);

	public GameState(DungeonGame game) {
		super(game);

		loadWorld();
		player = world.getPlayer();

	}

	@Override
	public void update(float delta) {
		if (isLevelEnded)
			return;
		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_ESCAPE))
			isPaused = !isPaused;
		if (isPaused)
			return;

		// update world
		world.update(delta);

		// if boss level
		if (world.hasBoss()) {

			// player - boss collision
			if (player.isAttacking()) {
				if (player.getAttackBox().overlaps(world.getBoss().getBox()))
					world.getBoss().hurt(0.1f);

			} else {
				if (player.getBox().overlaps(world.getBoss().getBox()))
					player.hurt(0.1f);
			}

			// fire attack if player is in sight
			if (world.getBoss().getRadarBox().overlaps(player.getBox())) {
				Vector2f location = new Vector2f(player.getX() + 16, player.getY() + 16);
				world.getBoss().fire(location);
			}

		} else {// else if a normal level

			// if player is attacking,hurt slimes or bat if they collide with attack box
			if (player.isAttacking()) {

				for (Slime slime : world.getSlimes())
					if (player.getAttackBox().overlaps(slime.getBox()))
						slime.hurt(0.1f);

				for (Bat bat : world.getBats())
					if (player.getAttackBox().overlaps(bat.getBox()))
						bat.hurt(0.1f);

			} else { // else if player is not attacking,hurt player if it collides with slime or bat

				for (Slime slime : world.getSlimes())
					if (player.getBox().overlaps(slime.getBox()))
						player.hurt(0.1f);

				for (Bat bat : world.getBats())
					if (player.getBox().overlaps(bat.getBox()))
						player.hurt(0.1f);

			}
			// System.out.println(player.getHp());

			// let slime follow player , if it is in sight
			for (Slime slime : world.getSlimes())
				slime.follow(player);

			// let bat attack player ,if it is in sight
			for (Bat bat : world.getBats())
				if (bat.getRadarBox().overlaps(player.getBox())) {
					Vector2f destination = new Vector2f(player.getX() + 16, player.getY() + 16);
					bat.fire(destination);
				}

			// remove dead slimes
			for (int i = world.getSlimes().size() - 1; i >= 0; i--)
				if (world.getSlimes().get(i).isDead())
					world.getSlimes().remove(i);
			// remove dead Bats
			for (int i = world.getBats().size() - 1; i >= 0; i--)
				if (world.getBats().get(i).isDead())
					world.getBats().remove(i);

		}

		// check if player collided with coin or potion
		for (PickupObject pickup : world.getPickupObjects())
			if (pickup.getBox().overlaps(player.getBox())) {

				switch (pickup.getType()) {
				case PickupObject.COIN:
					player.coinPicked();
					pickup.pick();
					break;
				case PickupObject.POTION:
					player.hpUp(2.5f);
					pickup.pick();
					break;
				case PickupObject.GUN:
					player.getGun().setFireDelay(0.7f);
					pickup.pick();
					break;
				}
			}

		// removed picked up items
		for (int i = world.getPickupObjects().size() - 1; i >= 0; i--)
			if (world.getPickupObjects().get(i).isPicked())
				world.getPickupObjects().remove(i);

		// check if player died
		if (player.isDead()) {
			isLevelEnded = true;
			game.restartLevel(this);
			return;
		}

		// check if player collides with a door,then move to next world
		for (int i = 0; i < world.getDoors().size(); i++)
			if (player.getBox().overlaps(world.getDoors().get(i).getBox())) {
				Door door = world.getDoors().get(i);
				if (door.getWorldName() <= 5) {
					world.setPlayer(null);
					world = WorldLoader.load("/maps/level1/" + door.getWorldName() +  ".txt");
					world.setPlayer(player);
					world.setHitListener(this);

					player.setTileMap(world.getTileMap());
					player.setLocation(door.getExitX(), door.getExitY());

					break;

				} else {
					if (world.getBoss().isDead()) {
						isLevelEnded = true;
						game.nextLevel(this);
					}
				}
			}

	}

	@Override
	public void render(Graphics2D g2) {
		world.render(g2);

		g2.setColor(Color.orange);
		g2.setFont(font);
		g2.drawString("Health : " + (int) player.getHp(), 5, font.getSize());
		g2.drawString("Coins : " + player.getCoins(), 5, font.getSize() * 2 + 10);

		if (isPaused) {
			g2.setFont(pauseFont);
			String msg = "Paused";
			float x = (Game.getGameWidth() - FontUtils.getTextWidth(pauseFont, msg))/2;
			float y = (Game.getGameHeight() - pauseFont.getSize())/2;
			g2.drawString("Paused", x,y);

		}
	}

	@Override
	public void hit(Projectile p, Gun shootingGun) {
		if (shootingGun == player.getGun() || shootingGun == player.getSpecialAbility()) {

			for (Slime slime : world.getSlimes())
				if (slime.getBox().overlaps(p.getBox()))
					slime.hurt(shootingGun.getHitDamage());

			for (Bat bat : world.getBats())
				if (bat.getBox().overlaps(p.getBox()))
					bat.hurt(shootingGun.getHitDamage());

			if (world.hasBoss()) {
				if (world.getBoss().getBox().overlaps(p.getBox()))
					world.getBoss().hurt(shootingGun.getHitDamage());
			}

		} else {
			if (player.getBox().overlaps(p.getBox()))
				player.hurt(shootingGun.getHitDamage());

		}

	}

	private void loadWorld() {
			world = WorldLoader.load("/maps/level1/1.txt");
			world.setHitListener(this);
	}

	public String getLvlName() {
		return lvlName;
	}

}
