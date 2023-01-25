package com.game.dungeongame.world.objects;

import java.awt.Graphics2D;

import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;

public class Door extends Entity{

	private int worldName;
	private int exitX,exitY;
	private CollisionBox box;
	
	public Door(float x, float y,int worldName,int exitX,int exitY) {
		super(x, y);
		setWorldName(worldName);
		setBox(new CollisionBox(x,y,100,100));
		setExitX(exitX);
		setExitY(exitY);
	}

	@Override
	public void update(float delta) {
		
		
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		box.render(g2, camera);
		
	}

	public int getWorldName() {
		return worldName;
	}

	public void setWorldName(int worldName) {
		this.worldName = worldName;
	}

	public CollisionBox getBox() {
		return box;
	}

	public void setBox(CollisionBox box) {
		this.box = box;
	}

	public int getExitY() {
		return exitY;
	}

	public void setExitY(int exitY) {
		this.exitY = exitY;
	}

	public int getExitX() {
		return exitX;
	}

	public void setExitX(int exitX) {
		this.exitX = exitX;
	}

}
