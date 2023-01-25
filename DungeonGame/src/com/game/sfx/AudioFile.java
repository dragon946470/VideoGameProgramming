package com.game.sfx;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class AudioFile {

	private AudioInputStream stream;
	private Clip clip;
	private DataLine.Info info;
	
	
	public AudioFile(AudioInputStream stream) {
		this.stream = stream;
		this.info = new DataLine.Info(Clip.class, this.stream.getFormat());
		
		try {
			this.clip = (Clip) AudioSystem.getLine(info);
			clip.open(this.stream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isPlayed() {
		return clip.getMicrosecondPosition() == clip.getMicrosecondLength();
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
		clip.flush();
	}
	
	public void setVolume(float value) {
	        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volume.setValue(value);
	    
	}
	
	public void reset() {
		clip.setFramePosition(0);
	}
}
