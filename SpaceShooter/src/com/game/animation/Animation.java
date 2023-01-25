package com.game.animation;

import java.awt.image.BufferedImage;

import com.game.gfx.AssetManager;

public class Animation {

	private BufferedImage[] frames;
	private int index;
	private float frameDelay;
	private float timer;
	private int step = 1;

	private boolean isRestartable = true;

	public Animation(BufferedImage[] frames, float frameDelay) {
		this.frames = frames;
		index = 0;
		this.frameDelay = frameDelay;
	}

	public Animation(String framesKey, float frameDelay) {
		this(AssetManager.getFrames(framesKey), frameDelay);
	}

	public void update(float delta) {
		timer += delta;
		if (timer >= frameDelay) {
			index += step;
			timer = 0;
		}

		if (index >= frames.length) {
			if (isRestartable)
				index = 0;
			else
				index = frames.length - 1;
		}
	}

	public BufferedImage getCurrentFrame(float stateTime) {
		int i = (int) (stateTime / frameDelay);
		if (i >= frames.length)
			i = frames.length - 1;
		return frames[i];
	}

	public void setIsRestartable(boolean isRestartable) {
		this.isRestartable = isRestartable;
	}

	public void reset() {
		index = 0;
		timer = 0;
	}

	public void setIndex(int i) {
		index = i;

	}

	public int getIndex() {
		return index;
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public BufferedImage[] getFrames() {
		return this.frames;
	}

	public boolean isAtLastIndex() {
		return index == frames.length - 1;
	}

	public boolean isComplete() {
		return isAtLastIndex();
	}

	public void copyState(Animation animation) {
		timer = animation.getTimer();
		index = animation.getIndex();
	}

	public float getTimer() {
		return timer;
	}

}
