package com.game.dungeongame.world.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.game.dungeongame.world.CollisionBox;
import com.game.dungeongame.world.Entity;
import com.game.dungeongame.world.map.Camera;
import com.game.gfx.AssetManager;

public class PickupObject extends Entity{

	public static final int COIN = 1;
	public static final int POTION = 2;
	public static final int GUN = 3;
	
	private BufferedImage image;
	private int width,height;
	private int type;
	private boolean isPicked;
	
	private CollisionBox box;

	public PickupObject(float x, float y,String imageName,int type,float scale) {
		super(x, y);
		image = AssetManager.getImage(imageName);
		this.type = type;
		
		width = (int) (image.getWidth() * scale);
		height = (int) (image.getHeight() * scale);
		
		box = new CollisionBox(x,y,width,height);
		
	}

	@Override
	public void update(float delta) {
		
		
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		g2.drawImage(image, Math.round(x - camera.getxOffset()),
				Math.round(y - camera.getyOffset()),width,height, null);
		
	}
	
	public void pick() {
		isPicked = true;
	}
	
	public boolean isPicked() {
		return isPicked;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		
	}

	public BufferedImage getImage() {
		return image;
	}

	public CollisionBox getBox() {
		return box;
	}

}
