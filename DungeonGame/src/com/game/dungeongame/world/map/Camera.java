package com.game.dungeongame.world.map;

import com.game.dungeongame.Game;
import com.game.dungeongame.world.Entity;

public class Camera {

	private float xOffset;
	private float yOffset;

	private int mapWidth, mapHeight;

	public Camera(float xOffset, float yOffset, int mapWidth, int mapHeight) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.mapWidth = mapWidth * TileMap.TILE_WIDTH;
		this.mapHeight = mapHeight * TileMap.TILE_HEIGHT;
	}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - Game.getGameWidth() / 2 + 0.0f;
		yOffset = e.getY() - Game.getGameHeight() / 2 + 0.0f;

		
		if (xOffset < 0)
			xOffset = 0;
		else if (xOffset > (mapWidth - Game.getGameWidth()))
			xOffset = mapWidth - Game.getGameWidth();
		

		if (yOffset < 0)
			yOffset = 0;
		else if (yOffset > mapHeight - Game.getGameWidth())
			yOffset = mapHeight - Game.getGameHeight();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight * TileMap.TILE_HEIGHT;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth * TileMap.TILE_WIDTH;
	}
}
