package com.game.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioLoader {


	public static AudioInputStream loadAudioInputStream(String path) {
		AudioInputStream stream = null;
		try {
			stream = AudioSystem.getAudioInputStream(AudioLoader.class.getResource(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
	}
}
