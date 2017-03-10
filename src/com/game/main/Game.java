package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	public Spawner spawner;
	private Menu menu;
	public AudioPlayer player;
	
	public enum STATE {
		Menu,
		Game
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "MyGame", this);
		
		this.handler = new Handler();

		
		this.addKeyListener(new KeyInput(handler));

		
		this.spawner = new Spawner(this.handler);
		
		this.hud = new HUD();
		this.menu = new Menu(this);
		this.addMouseListener(menu);
		
		player = new AudioPlayer();
		player.musicMap.get("background music").loop();
	
		/* wait for initilaization before starting */
		this.start();
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void run() {
		
		running = true;	
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		this.requestFocus();
		
		while(running) {		
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta > 1) {
				tick();
				delta--;
			}
			if (running) render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}	
	
	private void tick() {
		
		if (gameState == STATE.Game) {
			handler.tick();
			spawner.tick();
			handler.addObjects();
			handler.removeObjects();
			if (Spawner.health <= 0) {
				menu.setState(Menu.MENUSTATE.GameOver);
				this.gameState = STATE.Menu;
			}
		} else if (gameState == STATE.Menu) {
			menu.tick();
		}

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if (gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		} else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		g.dispose();
		bs.show();

	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	
	
	public void reset() {
		handler.objects.clear();
		Spawner.reset();	
	}


}
