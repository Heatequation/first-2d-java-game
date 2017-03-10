package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		
		if (x <= 0) velX = Math.max(velX, 0);
		if (x >= Game.WIDTH - 34) velX = Math.min(velX, 0);
		if (y <= 0) velY = Math.max(velY, 0);
		if (y >= Game.HEIGHT - 58) velY = Math.min(velY, 0);
		
		
		x += velX;
		y += velY;
		
		collision();

	}

	private void collision() {
		for(GameObject o: this.handler.objects) {
			if (o.id == ID.Enemy) {
				if (getBounds().intersects(o.getBounds())) {
					Spawner.health -= 2;
				}
			}
			if (o.id == ID.Coin) {
				if (getBounds().intersects(o.getBounds())) {
					Spawner.score += 100;
					this.handler.removeObject(o);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
