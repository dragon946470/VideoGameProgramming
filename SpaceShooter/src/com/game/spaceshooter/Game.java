package com.game.spaceshooter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;

import com.game.input.KeyManager;
import com.game.input.MouseManager;
import com.game.window.MainWindow;

public abstract class Game implements Runnable {

	protected static MainWindow mainFrame;
	protected static GameConfigurations gameConfigurations;

	protected Graphics2D graphics;
	private BufferStrategy bs;
	private GraphicsConfiguration gc;
	private VolatileImage vImage;
	protected Thread gameThread;
	protected int fps;
	private int framesThisSecond;
	private boolean running = true;
	
	public static final Font DEFAULT_FONT = new Font("Arial",Font.PLAIN,12);

	public static final KeyManager keyManager = new KeyManager();
	public static final MouseManager mouseManager = new MouseManager();

	public Game() {

	}

	public final void initialize(GameConfigurations config) {
		gameConfigurations = config;
		mainFrame = new MainWindow(config.getTitle(), config.getGameWidth(), config.getGameHeight(),
				config.fullScreen(),config.resizeable());
		mainFrame.getCanvas().setBackground(Color.BLACK);
		this.fps = config.getFps();
		gc = mainFrame.getCanvas().getGraphicsConfiguration();
		mainFrame.getFrame().addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent c) {
				if (!gameConfigurations.isScaling())
					vImage = gc.createCompatibleVolatileImage(getWidth(), getHeight());

			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		addKeyAdapter(keyManager);
		addMouseAdapter(mouseManager);
	}

	public abstract void create();

	public abstract void update(float delta);

	public abstract void render();

	private void preRender() {
		if (vImage == null) {
			gc = mainFrame.getCanvas().getGraphicsConfiguration();
			vImage = gc.createCompatibleVolatileImage(gameConfigurations.getGameWidth(),
					gameConfigurations.getGameHeight());
		}
		if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
			vImage = gc.createCompatibleVolatileImage(gameConfigurations.getGameWidth(),
					gameConfigurations.getGameHeight());
		}
		graphics = (Graphics2D) vImage.getGraphics();
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getWidth(), getHeight());

	}

	private void show() {
		graphics.dispose();
		if (bs == null) {
			mainFrame.getCanvas().createBufferStrategy(3);
			bs = mainFrame.getCanvas().getBufferStrategy();
		}
		graphics = (Graphics2D) bs.getDrawGraphics();

		if (gameConfigurations.isScaling()) {
			int width = getWidth(), height = getHeight();

			double thumbRatio = (double) width / (double) height;
			int imageWidth = vImage.getWidth();
			int imageHeight = vImage.getHeight();
			double aspectRatio = (double) imageWidth / (double) imageHeight;

			if (thumbRatio < aspectRatio) {
				height = (int) (width / aspectRatio);
			} else {
				width = (int) (height * aspectRatio);
			}

			graphics.drawImage(vImage, (getWidth() - width) / 2, (getHeight() - height) / 2, width, height, null);
		} else
			graphics.drawImage(vImage, 0, 0, null);

		graphics.dispose();
		bs.show();

	}

	public synchronized void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		create();
		double tickPerTime = 1000000000 / fps; 
		long lastTime = System.nanoTime();
		long lastUpdateTime = System.nanoTime();
		long now = 0;
		long timer = 0;
		int ticks = 0;

		int updates = 0;
		int maxUpdates = 5;

		while (running) {
			now = System.nanoTime();
			timer += (now - lastTime);

			updates = 0;
			while (now - lastUpdateTime >= tickPerTime) {
				float delta = (now - lastUpdateTime) / 1000000000.0f;
				keyManager.update(delta);
				delta = delta <= 0.016f ? delta : 0.016f;
				update(delta);
				lastUpdateTime += tickPerTime;
				updates++;

				if (updates > maxUpdates) {
					break;
				}
			}

			preRender();
			render();
			show();
			ticks++;
			lastTime = now;

			long timeTake = System.nanoTime() - now;
			if (tickPerTime > timeTake)
				try {
					Thread.sleep((long) ((tickPerTime - timeTake) / 1000000f));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			if (timer >= 1000000000) {
				// mainFrame.getFrame().setTitle("Frame Per Seconds : " + ticks);
				framesThisSecond = ticks;
				ticks = 0;
				timer = 0;
			}

		}

	}

	protected void renderfpsCount(Color color) {
		graphics.setFont(DEFAULT_FONT);
		graphics.setColor(color);
		graphics.drawString("FRAME PER SECOND : " + framesThisSecond,
				gameConfigurations.isScaling() ? gameConfigurations.getGameWidth() - 160 : getWidth() - 160,
				10 + graphics.getFont().getSize());
	}

	protected void renderfpsCount(Color color, int x, int y) {
		graphics.setColor(color);
		graphics.drawString("FRAME PER SECOND : " + framesThisSecond, x, y);
	}

	protected void setFPS(int fps) {
		this.fps = fps;
	}

	public void addKeyAdapter(KeyAdapter e) {
		mainFrame.getCanvas().addKeyListener(e);
		mainFrame.getFrame().addKeyListener(e);
	}
	
	public void addMouseAdapter(MouseAdapter e) {
		mainFrame.getCanvas().addMouseListener(e);
		mainFrame.getFrame().addMouseListener(e);		
		mainFrame.getCanvas().addMouseMotionListener(e);
		mainFrame.getFrame().addMouseMotionListener(e);
	}

	public void removeKeyAdapter(KeyAdapter e) {
		mainFrame.getCanvas().removeKeyListener(e);
		mainFrame.getFrame().removeKeyListener(e);
	}

	public static int getWidth() {
		return mainFrame.getCanvas().getWidth();
	}

	public static int getHeight() {
		return mainFrame.getCanvas().getHeight();
	}
	
	public static int getGameWidth() {
		return gameConfigurations.getGameWidth();
	}
	
	public static int getGameHeight() {
		return gameConfigurations.getGameHeight();
	}

}
