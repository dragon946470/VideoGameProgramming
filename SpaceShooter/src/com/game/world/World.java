package com.game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.game.spaceshooter.ships.Enemy;
import com.game.spaceshooter.ships.Player;
import com.game.world.map.BackGround;

public class World {

	private BackGround backGround;
	private Player player;

	private ArrayList<Enemy> enemies;
	
	public World() {
		backGround = new BackGround("background");
		enemies = new ArrayList<Enemy>();
	}
	
	public void update(float delta) {
		backGround.update(delta);
		player.update(delta);
		
		for(Enemy e : enemies)
			e.update(delta);
		
		for(int i = enemies.size() - 1;i >= 0;i--)
			if(enemies.get(i).isDead())
				enemies.remove(i);
	}
	
	public void render(Graphics2D g2) {
		backGround.render(g2);	
		player.render(g2);
		
		for(Enemy e : enemies)
			e.render(g2);
		
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getEnemiesCount() {
		return enemies.size();
	}
}
