package com.game.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import com.game.sfx.AudioFile;
import com.game.utils.ImageLoader;
import com.game.utils.ImageRotator;

public class AssetManager {

	private static HashMap<String, AudioFile> allSounds = new HashMap<String, AudioFile>();
	private static HashMap<String, BufferedImage> allImages = new HashMap<String, BufferedImage>();
	private static HashMap<String, BufferedImage[]> allAnimations = new HashMap<String, BufferedImage[]>();

	public static void load() {
		loadImages();
		loadAnimations();
		loadAudio();

	}

	private static void loadImages() {

		/*TILES*/
		SpriteSheet tileSheet = new SpriteSheet("/tilesheet.png");

		int count = 1;

		BufferedImage image = tileSheet.crop(112, 0, 16, 16);
		allImages.put("" + count++, image);
		allImages.put("" + count++, ImageRotator.rotate(image, 90));
		allImages.put("" + count++, ImageRotator.rotate(image, 180));

		allImages.put("" + count++, tileSheet.crop(112 + 16, 0, 16, 16));
		allImages.put("" + count++, tileSheet.crop(112 + 16, 0, 16, 16));
		allImages.put("" + count++, tileSheet.crop(112, 16, 16, 16));
		allImages.put("" + count++, tileSheet.crop(112 + 16, 16, 16, 16));
		allImages.put("" + count++, tileSheet.crop(112 + 16, 16, 16, 16));

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 7; j++) {
				if (i == 0 && j < 3) {
					BufferedImage img = tileSheet.crop(j * 16, i * 16, 16, 16);
					allImages.put("" + count++, img);
					allImages.put("" + count++, ImageRotator.rotate(img, 90));
					allImages.put("" + count++, ImageRotator.rotate(img, 180));
					allImages.put("" + count++, ImageRotator.rotate(img, 270));
				} else
					allImages.put("" + count++, tileSheet.crop(j * 16, i * 16, 16, 16));

			}

		//background
		allImages.put("background", tileSheet.crop(64, 96, 16, 16));
		
		
		/*	WORLD OBJECTS	*/
		allImages.put("fire", tileSheet.crop(0, 160, 8, 21));
		allImages.put("skull", tileSheet.crop(16, 144, 16,16));
		allImages.put("coin", tileSheet.crop(0, 144, 16,16));
		allImages.put("potion", tileSheet.crop(0,181,9,11));
		allImages.put("gun", tileSheet.crop(0, 193, 21, 17));

	}

	private static void loadAnimations() {

		/* PLAYER ANIMATIONS */
		SpriteSheet playerSheet = new SpriteSheet("/playersheet.png");

		// walk animations
		BufferedImage[] walkLeft, walkRight, walkUp, walkDown;
		int numFrames = 8;

		walkRight = new BufferedImage[numFrames];
		walkLeft = new BufferedImage[numFrames];
		walkDown = new BufferedImage[numFrames];
		walkUp = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {
			walkRight[i] = playerSheet.crop(32 * i, 0, 32, 32);
			walkLeft[i] = playerSheet.crop(32 * i, 32, 32, 32);
			walkDown[i] = playerSheet.crop(32 * i, 64, 32, 32);
			walkUp[i] = playerSheet.crop(32 * i, 96, 32, 32);
			;

		}
		allAnimations.put("walk_right", walkRight);
		allAnimations.put("walk_left", walkLeft);
		allAnimations.put("walk_down", walkDown);
		allAnimations.put("walk_up", walkUp);

		// die animation
		BufferedImage[] die = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++)
			die[i] = playerSheet.crop(32 * i, 128, 32, 32);
		allAnimations.put("die", die);

		// attack animation
		BufferedImage[] attackRight, attackLeft, attackDown, attackUp;
		attackRight = new BufferedImage[numFrames];
		attackLeft = new BufferedImage[numFrames];
		attackDown = new BufferedImage[numFrames];
		attackUp = new BufferedImage[numFrames];

		for (int i = 0; i < numFrames; i++) {
			attackRight[i] = playerSheet.crop(32 * i, 160, 32, 32);
			attackLeft[i] = playerSheet.crop(32 * i, 192, 32, 32);
			attackDown[i] = playerSheet.crop(32 * i, 224, 32, 32);
			attackUp[i] = playerSheet.crop(32 * i, 256, 32, 32);
		}

		allAnimations.put("attack_right", attackRight);
		allAnimations.put("attack_left", attackLeft);
		allAnimations.put("attack_down", attackDown);
		allAnimations.put("attack_up", attackUp);

		/* SLIME ANIMATIONS */
		SpriteSheet slimeSheet = new SpriteSheet("/slimesheet.png");
		int numFrames1 = 4;

		BufferedImage[] slimeWalkLeft, slimeWalkRight, slimeWalkDown, slimeWalkUp;
		slimeWalkLeft = new BufferedImage[numFrames1];
		slimeWalkRight = new BufferedImage[numFrames1];
		slimeWalkDown = new BufferedImage[numFrames1];
		slimeWalkUp = new BufferedImage[numFrames1];

		for (int i = 0; i < numFrames1; i++) {
			slimeWalkDown[i] = slimeSheet.crop(32 * i, 0, 32, 32);
			slimeWalkRight[i] = slimeSheet.crop(32 * i, 32, 32, 32);
			slimeWalkUp[i] = slimeSheet.crop(32 * i, 64, 32, 32);
			slimeWalkLeft[i] = slimeSheet.crop(32 * i, 96, 32, 32);
		}

		allAnimations.put("slime_walk_up", slimeWalkUp);
		allAnimations.put("slime_walk_down", slimeWalkDown);
		allAnimations.put("slime_walk_right", slimeWalkRight);
		allAnimations.put("slime_walk_left", slimeWalkLeft);

		/* BAT ANIMATIONS */
		SpriteSheet batSheet = new SpriteSheet("/batsheet.png");
		int numFrames2 = 3;

		BufferedImage[] batMoveRight, batMoveLeft;
		batMoveRight = new BufferedImage[numFrames2];
		batMoveLeft = new BufferedImage[numFrames2];
		for (int i = 0; i < numFrames2; i++) {
			batMoveRight[i] = batSheet.crop(16 * i, 0, 16, 16);
			batMoveLeft[i] = horizontalFlip(batMoveRight[i]);
		}

		allAnimations.put("bat_move_right", batMoveRight);
		allAnimations.put("bat_move_left", batMoveLeft);

		/* PROJECTILE ANIMATIONS */
		SpriteSheet projectileSheet = new SpriteSheet("/projectiles.png");

		// projectile 1
		int numFrames3 = 3;
		BufferedImage[] projectile1 = new BufferedImage[numFrames3];
		for (int i = 0; i < numFrames3; i++)
			projectile1[i] = projectileSheet.crop(26 + i * 64, 1226, 64, 64);
		allAnimations.put("projectile_1", projectile1);

		// projectile 2
		int numFrames4 = 5;
		BufferedImage[] projectile2 = new BufferedImage[4];
		int index = 0;
		for (int i = numFrames4 - 1; i > 0; i--)
			projectile2[index++] = projectileSheet.crop(118 + i * 46, 1418, 46, 46);
		allAnimations.put("projectile_2", projectile2);

		// projectile 3
		int numFrames5 = 3;
		BufferedImage[] projectile3 = new BufferedImage[numFrames5];
		for (int i = 0; i < numFrames5; i++)
			projectile3[i] = projectileSheet.crop(1187 + i * 37, 1798, 37, 37);
		allAnimations.put("projectile_3", projectile3);

		// projectile 3
		int numFrames6 = 5;
		BufferedImage[] projectile4 = new BufferedImage[numFrames6];
		for (int i = 0; i < numFrames6; i++)
			projectile4[i] = projectileSheet.crop(i * 64, 2636,64,64);
		allAnimations.put("projectile_4", projectile4);
		
		
		/*	BOSS ANIMATIONS	*/
		BufferedImage[] idleRight = new BufferedImage[6];
		BufferedImage[]idleLeft = new BufferedImage[6];
		BufferedImage[] bossWalkLeft = new BufferedImage[6];
		BufferedImage[] bossWalkRight = new BufferedImage[6];
		
		for(int i = 0; i < 6;i++) {
			idleRight[i] = ImageLoader.loadImage("/boss/Golem_Idle_" + (i+1) + ".png");
			idleLeft[i] = horizontalFlip(idleRight[i]);
			bossWalkRight[i] = ImageLoader.loadImage("/boss/Golem_Walk_" + (i+1) + ".png");
			bossWalkLeft[i] = horizontalFlip(bossWalkRight[i]);
		}
		
		allAnimations.put("boss_idle_right", idleRight);
		allAnimations.put("boss_idle_left", idleLeft);
		allAnimations.put("boss_move_right", bossWalkRight);
		allAnimations.put("boss_move_left", bossWalkLeft);

	}

	private static void loadAudio() {

	}

	private static BufferedImage horizontalFlip(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return flippedImage;
	}

	public static String getKeyOfImage(BufferedImage image) {
		for (Entry<String, BufferedImage> entry : allImages.entrySet())
			if (entry.getValue() == image) {
				return entry.getKey();

			}

		return null;
	}

	public static AudioFile getAudioFile(String key) {
		return allSounds.get(key);
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
