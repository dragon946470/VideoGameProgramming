package com.game.sfx;

public class SoundPlayer implements Runnable {

	private AudioFile BGM;
	private AudioFile soundEffect,soundEffect2;
	private Thread thread;

	private boolean playingBackGround;
	private boolean playingSoundEffect,playingSoundEffect2;

	public SoundPlayer() {
		thread = new Thread(this);
		thread.start();
	}

	public void playBackGroundMusic(AudioFile audio) {

		if (audio != null && audio != BGM) {
			stop();
			this.BGM = audio;
			BGM.setVolume(-8f);
			playingBackGround = true;
		}

	}

	public void stop() {
		if (BGM != null) {
			BGM.stop();
			BGM.reset();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (playingBackGround) {
				BGM.loop();
				playingBackGround = false;
			}
			if (playingSoundEffect) {
				soundEffect.play();
				playingSoundEffect = false;
			}
			
			if(playingSoundEffect2) {
				soundEffect2.play();
				playingSoundEffect2 = false;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void playSoundEffect(AudioFile soundEffect) {
		if (soundEffect != null)
			{
				if (this.soundEffect != null)
					this.soundEffect.reset();
				this.soundEffect = soundEffect;

				playingSoundEffect = true;
			}

	}
	
	public void playSoundEffect2(AudioFile soundEffect) {
		if (soundEffect != null)
			{
				if (this.soundEffect2 != null)
					this.soundEffect2.reset();
				this.soundEffect2 = soundEffect;

				playingSoundEffect2 = true;
			}

	}
}
