package com.game.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.game.utils.ImageLoader;

public class AssetManager {

	private static HashMap<String, BufferedImage> allImages = new HashMap<String, BufferedImage>();
	private static HashMap<String, BufferedImage[]> allAnimations = new HashMap<String, BufferedImage[]>();

	public static void load() {
		loadImages();
		loadAnimations();

	}

	private static void loadImages() {
		/**/
	}

	private static void loadAnimations() {
		/* BACKGROUND ANIMATION */

		BufferedImage[] background = new BufferedImage[2];
		background[0] = ImageLoader.loadImage("/background_1.png");
		background[1] = ImageLoader.loadImage("/background_2.png");

		allAnimations.put("background", background);

		/* PLAYER SHIP ANIMATION */
		SpriteSheet playerSheet = new SpriteSheet("/player_ship.png");

		BufferedImage[] playerShip = new BufferedImage[2];
		for (int i = 0; i < 2; i++)
			playerShip[i] = playerSheet.crop(0, 24 * i, 16, 24);

		allAnimations.put("playerShip", playerShip);

		/* BULLET ANIMATIONS */
		SpriteSheet bulletSheet = new SpriteSheet("/bullets_sheet.png");

		// player bullet
		BufferedImage[] playerBullet = new BufferedImage[4];
		playerBullet[0] = bulletSheet.crop(0, 73, 10, 11);
		playerBullet[1] = bulletSheet.crop(0, 52, 10, 15);
		playerBullet[2] = bulletSheet.crop(0, 28, 10, 18);
		playerBullet[3] = bulletSheet.crop(0, 0, 10, 20);
		
		allAnimations.put("playerBullet", playerBullet);
		
		//enemy bullet
		BufferedImage[] bullet1 = new BufferedImage[1];
		BufferedImage[] bullet2 = new BufferedImage[1];
		
		bullet1[0] = bulletSheet.crop(23, 50, 17, 17);
		bullet2[0] = bulletSheet.crop(23, 67, 17, 17);

		allAnimations.put("bullet1", bullet1);
		allAnimations.put("bullet2", bullet2);

		/* ENEMY SHIP ANIMATIONS */
		SpriteSheet enemy1Sheet = new SpriteSheet("/enemy-small.png");
		SpriteSheet enemy2Sheet = new SpriteSheet("/enemy-medium.png");
		SpriteSheet enemy3Sheet = new SpriteSheet("/enemy-big.png");

		BufferedImage[] enemy1 = new BufferedImage[2];
		BufferedImage[] enemy2 = new BufferedImage[2];
		BufferedImage[] enemy3 = new BufferedImage[2];

		for (int i = 0; i < 2; i++) {
			enemy1[i] = enemy1Sheet.crop(16 * i, 0, 16, 16);
			enemy2[i] = enemy2Sheet.crop(32 * i, 0, 32, 16);
			enemy3[i] = enemy3Sheet.crop(32 * i, 0, 32, 32);
		}

		allAnimations.put("enemy1", enemy1);
		allAnimations.put("enemy2", enemy2);
		allAnimations.put("enemy3", enemy3);
	}


	public static BufferedImage getImage(String key) {
		return allImages.get(key);
	}

	public static BufferedImage[] getFrames(String key) {
		return allAnimations.get(key);
	}

	/*
	 * public void loadFont(String path) {
	 * 
	 * }
	 * 
	 * public Font getFont(String key) {
	 * 
	 * }
	 */
}
