package com.game.dungeongame.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.game.dungeongame.Game;
import com.game.dungeongame.world.characters.Bat;
import com.game.dungeongame.world.characters.Boss;
import com.game.dungeongame.world.characters.Player;
import com.game.dungeongame.world.characters.Slime;
import com.game.dungeongame.world.map.Camera;
import com.game.dungeongame.world.map.TileMap;
import com.game.dungeongame.world.objects.DecoObject;
import com.game.dungeongame.world.objects.Door;
import com.game.dungeongame.world.objects.PickupObject;
import com.game.dungeongame.world.projectiles.ProjectileHitListener;
import com.game.gfx.AssetManager;

public class World {

	private BufferedImage background;
	private TileMap tileMap;
	private Camera camera;

	private ArrayList<DecoObject> decoObjects;
	private ArrayList<PickupObject> pickupObjects;
	private ArrayList<Slime> slimes;
	private ArrayList<Bat> bats;

	private ArrayList<Door> doors;

	private Player player;
	private ProjectileHitListener hitListener;

	private Boss boss;

	public World(TileMap tileMap, ArrayList<DecoObject> decoObjects, ArrayList<PickupObject> pickupObjects,
			ArrayList<Slime> slimes, ArrayList<Bat> bats) {
		background = AssetManager.getImage("background");
		this.tileMap = tileMap;
		camera = new Camera(0, 0, tileMap.getCols(), tileMap.getRows());

		this.decoObjects = decoObjects;
		this.pickupObjects = pickupObjects;
		this.slimes = slimes;
		this.bats = bats;
	}

	public void update(float delta) {
		player.update(delta);

		if (boss != null)
			boss.update(delta);
		// slimes
		for (int i = 0; i < slimes.size(); i++)
			slimes.get(i).update(delta);
		// bats
		for (int i = 0; i < bats.size(); i++)
			bats.get(i).update(delta);
	}

	public void render(Graphics2D g2) {
		g2.drawImage(background, 0, 0, Game.getGameWidth(), Game.getGameHeight(), null);
		tileMap.render(g2, camera);

		// deco objects
		for (int i = 0; i < decoObjects.size(); i++)
			decoObjects.get(i).render(g2, camera);
		// pickup objects
		for (int i = 0; i < pickupObjects.size(); i++)
			pickupObjects.get(i).render(g2, camera);

		// slimes
		for (int i = 0; i < slimes.size(); i++)
			slimes.get(i).render(g2, camera);
		// bats
		for (int i = 0; i < bats.size(); i++)
			bats.get(i).render(g2, camera);

		// for(int i = 0; i < doors.size();i++)
		// doors.get(i).render(g2, camera);

		// boss
		if (boss != null)
			boss.render(g2, camera);

		// player
		player.render(g2, camera);
	}

	public boolean hasBoss() {
		return boss != null;
	}

	public void drawDebug() {
		if (player != null)
			player.setDrawDebug(true);
		if (boss != null)
			boss.setDrawDebug(true);
		for (Slime slime : slimes)
			slime.setDrawDebug(true);
		for (Bat bat : bats)
			bat.setDrawDebug(true);
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public Camera getCamera() {
		return camera;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ProjectileHitListener getHitListener() {
		return hitListener;
	}

	public void setHitListener(ProjectileHitListener hitListener) {
		this.hitListener = hitListener;

		if (player != null)
			player.setGunHitListener(hitListener);

		if (boss != null)
			boss.setHitListener(hitListener);

		for (int i = 0; i < bats.size(); i++)
			bats.get(i).setHitListener(hitListener);
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}

	public ArrayList<DecoObject> getDecoObjects() {
		return decoObjects;
	}

	public void setDecoObjects(ArrayList<DecoObject> decoObjects) {
		this.decoObjects = decoObjects;
	}

	public ArrayList<PickupObject> getPickupObjects() {
		return pickupObjects;
	}

	public void setPickupObjects(ArrayList<PickupObject> pickupObjects) {
		this.pickupObjects = pickupObjects;
	}

	public ArrayList<Slime> getSlimes() {
		return slimes;
	}

	public void setSlimes(ArrayList<Slime> slimes) {
		this.slimes = slimes;
	}

	public ArrayList<Bat> getBats() {
		return bats;
	}

	public void setBats(ArrayList<Bat> bats) {
		this.bats = bats;
	}

	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

}
