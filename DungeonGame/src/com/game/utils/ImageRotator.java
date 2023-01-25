package com.game.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageRotator {

	
	public static BufferedImage rotate(BufferedImage image,double angle) {
		BufferedImage rotatedImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = (Graphics2D) rotatedImage.getGraphics();
		AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
		at.rotate(Math.toRadians(angle),image.getWidth()/2,image.getHeight()/2);
		
		g2.drawImage(image,at,null);
		
		return rotatedImage;
	}
}
