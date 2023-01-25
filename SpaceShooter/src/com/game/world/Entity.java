package com.game.world;

import java.awt.Graphics2D;

import com.game.utils.Vector2f;

public abstract class Entity {

	protected Vector2f position;
	
	public Entity(float x,float y) {
		position = new Vector2f(x,y);
	}
	
	public abstract void update(float delta);
	public abstract void render(Graphics2D g2);

	
	public void setLocation(float x,float y) {
		position.x = x;
		position.y = y;
	}
	public float getX() {
		return position.x;
	}

	public void setX(float x) {
		position.x = x;
	}

	public float getY() {
		return position.y;
	}

	public void setY(float y) {
		position.y = y;
	}
	
	
	
}
