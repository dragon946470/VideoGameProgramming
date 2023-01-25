package com.game.utils;

import java.util.Scanner;

public class TextLoader {

	public static String loadTextFileAsString(String path) {

		try (Scanner scan = new Scanner(TextLoader.class.getResourceAsStream(path))) {
			String file = "";
			while (scan.hasNext()) {
				file += scan.next() + " ";
			}
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	

}
