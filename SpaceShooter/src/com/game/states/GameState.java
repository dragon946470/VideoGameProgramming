package com.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.spaceshooter.Game;
import com.game.spaceshooter.SpaceShooterGame;
import com.game.spaceshooter.gun.Bullet;
import com.game.spaceshooter.ships.Enemy;
import com.game.spaceshooter.ships.Player;
import com.game.utils.FontUtils;
import com.game.world.EnemyWave;
import com.game.world.World;

public class GameState extends State {

	private World world;
	private EnemyWave wave;
	private int score;

	private boolean isPaused;
	private Font pauseFont = new Font("Monospace", Font.BOLD, 20);

	private boolean isGameOver;
	private Font gameOverFont = new Font("Monospace", Font.BOLD, 25);
	private Font pressSpaceFont = new Font("Arial", Font.PLAIN, 20);

	public GameState(SpaceShooterGame game) {
		super(game);

		world = new World();
		world.setPlayer(new Player(200, 200));

		wave = new EnemyWave(world);
	}

	@Override
	public void update(float delta) {
		if (Game.keyManager.isKeyJustPressed(KeyEvent.VK_ESCAPE))
			isPaused = !isPaused;

		if (isGameOver && Game.keyManager.isKeyJustPressed(KeyEvent.VK_SPACE))
			game.showMenu();

		if (isPaused)
			return;
		wave.update(delta);
		world.update(delta);

		// check player-enemy collision
		for (Enemy e : world.getEnemies())
			if (world.getPlayer().getBox().overlaps(e.getBox())) {
				world.getPlayer().hurt(0.2f);
			}

		// check enemy - player bullet collision
		for (Enemy e : world.getEnemies())
			for (Bullet b : world.getPlayer().getGun().getBullets())
				if (e.getBox().overlaps(b.getBox())) {
					e.hurt(2f);
					b.die();

					// increase score
					if (e.isDead())
						score += 5;
				}

		// check player - enemy bullet collision
		for (Enemy e : world.getEnemies())
			if (e.getGun() != null)
				for (Bullet b : e.getGun().getBullets())
					if (world.getPlayer().getBox().overlaps(b.getBox())) {
						world.getPlayer().hurt(0.2f);
						b.die();
					}

		// check if player die
		if (world.getPlayer().isDead())
			isGameOver = true;

	}

	@Override
	public void render(Graphics2D g2) {
		world.render(g2);

		// draw hp and score
		g2.setColor(Color.RED);
		g2.drawString("HP: " + (int) world.getPlayer().getHp(), 10, 20);
		g2.drawString("SCORE: " + score, 10, 40);

		// paused
		if (isPaused) {
			g2.setFont(pauseFont);
			String paused = "PAUSED";
			g2.drawString(paused, Game.getGameWidth() / 2 - FontUtils.getTextWidth(pauseFont, paused) / 2,
					Game.getGameHeight() / 2 - pauseFont.getSize() / 2);
		}

		// game over
		if (isGameOver) {
			// draw game over
			g2.setFont(gameOverFont);
			String gameOver = "GAME OVER";
			int y = Game.getGameHeight() / 2 - gameOverFont.getSize() / 2;
			g2.drawString(gameOver, Game.getGameWidth() / 2 - FontUtils.getTextWidth(gameOverFont, gameOver) / 2, y);

			// draw press space to continue
			g2.setFont(pressSpaceFont);
			String pressSpace = "Press Space to return to menu";
			g2.drawString(pressSpace, Game.getGameWidth() / 2 - FontUtils.getTextWidth(pressSpaceFont, pressSpace) / 2,
					y + 40);

		}
	}

	public void reset() {
		world.getPlayer().setLocation(Game.getGameHeight() - 50, Game.getWidth() / 2);
		world.getEnemies().clear();

		isPaused = false;
		isGameOver = false;

	}

}
