package com.game.dungeongame.world.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;
import com.game.gfx.AssetManager;

public class DecoObject extends Entity {

	private BufferedImage image;
	private int width, height;

	public DecoObject(float x, float y, String imageName, float scale) {
		super(x, y);
		image = AssetManager.getImage(imageName);

		width = (int) (image.getWidth() * scale);
		height = (int) (image.getHeight() * scale);

	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		g2.drawImage(image, Math.round(x - camera.getxOffset()),
				Math.round(y - camera.getyOffset()),width,height, null);
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		
	}

	public BufferedImage getImage() {
		return image;
	}

}
