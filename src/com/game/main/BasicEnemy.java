package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	private Handler handler;

	public BasicEnemy(int x, int y, int vel, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX = vel;
		velY = vel;
		this.handler = handler;
	}

	@Override
	public void tick() {
		
		
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		if (y <= 0 || y >= Game.HEIGHT - 40) velY *= -1;
		
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
