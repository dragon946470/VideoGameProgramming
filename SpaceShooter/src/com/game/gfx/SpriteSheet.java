package com.game.gfx;

import java.awt.image.BufferedImage;

import com.game.utils.ImageLoader;

public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(String path) {
		this.sheet = (ImageLoader.loadImage(path));
	}

	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public BufferedImage getImage() {
		return sheet;
	}

}
