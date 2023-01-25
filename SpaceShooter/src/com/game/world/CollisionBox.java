package com.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.utils.Vector2f;

public class CollisionBox {

	public float x, y, width, height;
	public Vector2f topLeft;
	public Vector2f topRight;
	public Vector2f downLeft;
	public Vector2f downRight;

	private Color debugColor = Color.BLACK;

	public CollisionBox(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		topLeft = new Vector2f();
		topRight = new Vector2f();
		downLeft = new Vector2f();
		downRight = new Vector2f();

		update();

	}

	public void move(float dx, float dy) {
		x += dx;
		y += dy;
		update();
	}

	public void render(Graphics2D g2) {
		g2.setColor(debugColor);
		g2.drawRect((int) x, (int) y, (int) width, (int) height);
	}

	public boolean overlaps(CollisionBox r) {
		return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
	}

	private void update() {
		topLeft.setLocation(x, y);
		topRight.setLocation(x + width, y);

		downLeft.setLocation(x, y + height);
		downRight.setLocation(x + width, y + height);
	}

	public void setColor(Color color) {
		debugColor = color;
	}
}
