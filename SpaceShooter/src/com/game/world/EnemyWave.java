package com.game.world;

import java.util.Random;

import com.game.spaceshooter.Game;
import com.game.spaceshooter.ships.AIEnemy;
import com.game.spaceshooter.ships.Enemy;
import com.game.spaceshooter.ships.GunEnemy;
import com.game.spaceshooter.ships.SimpleEnemy;

public class EnemyWave {

	private World world;
	private float aiEnemySpawnRate = 0.2f;
	private float gunEnemySpawnRate = 0.5f;

	private int maxEnemies = 5;
	private float spawnDelay = 1f;
	private float spawnDelayCount;
	private Random random;

	public EnemyWave(World world) {
		this.world = world;
		random = new Random();
	}

	public void update(float delta) {
		spawnDelayCount += delta;
		if (spawnDelayCount >= spawnDelay) {
			spawnDelayCount = 0;

			if (world.getEnemiesCount() >= maxEnemies)
				return;

			Enemy enemy = null;

			// spawn location
			float spawnX = random.nextInt(Game.getGameWidth() - 32);
			float spawnY = -32;

			// spawn enemy
			double chance = Math.random();
			if (chance <= aiEnemySpawnRate) {
				AIEnemy e = new AIEnemy(spawnX, spawnY);
				e.follow(world.getPlayer());
				enemy = e;
			} else if (chance <= gunEnemySpawnRate)
				enemy = new GunEnemy(spawnX, spawnY);
			else
				enemy = new SimpleEnemy(spawnX, spawnY);

			// add enemy to world
			world.addEnemy(enemy);

		}
	}

}
