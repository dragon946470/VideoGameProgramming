package com.game.dungeongame.world.map;

import java.awt.Graphics2D;

import com.game.dungeongame.Game;
import com.game.utils.Vector2f;

public class TileMap {

	public static  int TILE_WIDTH = 32;
	public static  int TILE_HEIGHT = 32;

	private Tile[][] tiles;
	private int rows, cols;

	public TileMap(int rows, int cols, Tile[][] tiles) {
		this.rows = rows;
		this.cols = cols;

		this.tiles = tiles;
	}

	public boolean isSolidTile(float x, float y) {

		int row = (int) (y / TILE_HEIGHT);
		int col = (int) (x / TILE_WIDTH);

		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;

		return tiles[row][col].isSolidTile();
	}

	public boolean isValidTile(float x, float y) {
		int row = (int) (y / TILE_HEIGHT);
		int col = (int) (x / TILE_WIDTH);

		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;

		return true;
	}

	public boolean isSolidTile(Vector2f p) {
		return isSolidTile(p.x, p.y);
	}

	public void render(Graphics2D g2, Camera camera) {
		int xStart = (int) Math.max(0, camera.getxOffset() / TILE_WIDTH);
		int xEnd = (int) Math.min(cols, (camera.getxOffset() + Game.getWidth()) / TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, camera.getyOffset() / TILE_HEIGHT);
		int yEnd = (int) Math.min(rows, (camera.getyOffset() + Game.getHeight()) / TILE_HEIGHT + 1);

		for (int i = yStart; i < yEnd; i++) {
			for (int j = xStart; j < xEnd; j++) {
				if (!tiles[i][j].isEmptyTile())
					g2.drawImage(tiles[i][j].getImage(), Math.round(j * TILE_WIDTH - camera.getxOffset()),
							Math.round(i * TILE_HEIGHT - camera.getyOffset()), TILE_WIDTH, TILE_HEIGHT, null);
			}
		}

	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

}
