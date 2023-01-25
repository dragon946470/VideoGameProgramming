package com.game.world.map;

import java.awt.Graphics2D;

import com.game.animation.Animation;
import com.game.spaceshooter.Game;

public class BackGround {

	private Animation animation;

	public BackGround(String imageKey) {
		animation = new Animation(imageKey,5.5f);
	}

	public void update(float delta) {
		animation.update(delta);
	}
	
	public void render(Graphics2D g2) {
		g2.drawImage(animation.getCurrentFrame(),0,0,Game.getGameWidth(),Game.getGameHeight(),null);
	}
}
