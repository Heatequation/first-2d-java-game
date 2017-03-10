package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullet extends GameObject {
	
	private Handler handler;
	private Random r = new Random();

	public Boss1Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX = r.nextInt(10) - 5;
		velY = 3;
		this.handler = handler;
	}

	@Override
	public void tick() {
		
		if (x <= 0 || x >= Game.WIDTH - 16d) velX *= -1;
		if (y <= 0 || y >= Game.HEIGHT) velY *= -1;
		
		if (y >= Game.HEIGHT) handler.removeObject(this);
		
		x += velX;
		y += velY;
		
		this.handler.addObject(
				new Trail(x,y,ID.Trail,this.handler,Color.red,16,16)
					);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 16, 16);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
