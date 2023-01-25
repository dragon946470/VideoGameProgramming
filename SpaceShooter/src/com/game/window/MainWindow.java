package com.game.window;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame frame;
	private Canvas canvas;

	public MainWindow(String name, int width, int height,boolean fullScreen,boolean resizeable) {
		frame = new JFrame(name);

		canvas = new Canvas();
		canvas.setSize(width, height);
		frame.setSize(width,height);
		frame.setResizable(resizeable);
		//canvas.setPreferredSize(new Dimension(width,height));
        frame.add(canvas);
        if(fullScreen)makeFullScreen();
		
		frame.pack();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

	private void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
			frame.setUndecorated(true);
			frame.setResizable(false);
			frame.setIgnoreRepaint(true);
			gd.setFullScreenWindow(frame);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
