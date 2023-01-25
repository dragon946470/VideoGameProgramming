package com.game.dungeongame.world;

import java.awt.Graphics2D;

import com.game.dungeongame.world.map.Camera;

public abstract class Entity {

	protected float x,y;
	
	public Entity(float x,float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update(float delta);
	public abstract void render(Graphics2D g2,Camera camera);

	
	public void setLocation(float x,float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
