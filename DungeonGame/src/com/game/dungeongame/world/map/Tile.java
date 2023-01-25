package com.game.dungeongame.world.map;

import java.awt.image.BufferedImage;

import com.game.gfx.AssetManager;

public class Tile {

	public static final int EMPTY = 0;
	public static final int SOLID = 1;
	public static final int NON_SOLID = 2;
	
	private BufferedImage img;
	private int type;
	
	
	public Tile( String imageKey,int type) {
		this.img = AssetManager.getImage(imageKey);
		this.type = type;
	}

	public BufferedImage getImage() {
		return img;
	}
	
	public boolean isSolidTile() {
		return type == SOLID;
	}
	
	public boolean isEmptyTile() {
		return type == EMPTY;
	}
	
	public int getType() {
		return type;
	}

}
