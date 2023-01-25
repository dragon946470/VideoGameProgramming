package com.game.dungeongame.world;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.dungeongame.world.map.Camera;
import com.game.utils.Vector2f;

public class CollisionBox {

	public float x, y, width, height;
	private Color debugColor = Color.BLACK;

	public CollisionBox(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void move(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	public void move(Vector2f displacement) {
		x += displacement.x;
		y+= displacement.y;
	}

	public void render(Graphics2D g2, Camera camera) {
		g2.setColor(debugColor);
		g2.drawRect(Math.round(x - camera.getxOffset()), Math.round(y - camera.getyOffset()), (int) width,
				(int) height);
	}

	public boolean overlaps(CollisionBox r) {
		return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setColor(Color color) {
		debugColor = color;
	}

	public void set(CollisionBox box) {
		x = box.x;
		y = box.y;
		width = box.width;
		height = box.height;

	}
	
	public void set(float x,float y,float width,float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
