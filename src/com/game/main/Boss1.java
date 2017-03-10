package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1 extends GameObject {
	
	private Handler handler;
	private int timer = 100;
	private boolean nudge = true;
	private Random r = new Random();
	private int spawnRate;

	public Boss1(int x, int y, int spawnRate, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX = 0;
		velY = 1;
		this.spawnRate = spawnRate;
		this.handler = handler;
	}

	@Override
	public void tick() {
		

		/*if (y <= 0 || y >= Game.HEIGHT) velY *= -1;*/
		if (timer <= 0) {
			if (nudge) {
				velY = 0;
				velX = 5;
				nudge = false;
			}
			if (x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
			
			int spawn = r.nextInt(spawnRate);
				if (spawn == 0) {
					
					int dx = 16;
					if (velX < 0) dx *= -1;
					
					this.handler.addObject(
							new Boss1Bullet(x + 32 + dx,y + 60,ID.Enemy,this.handler)
								);
				}

		}
		else --timer;
		
		x += velX;
		y += velY;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 64, 64);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,64,64);
	}

}
