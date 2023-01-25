package com.game.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontLoader {

	public static Font loadFont(String path, float size) {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path))
					.deriveFont(size);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return font;
	}
}
