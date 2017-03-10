package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicCoin extends GameObject {

	public BasicCoin(int x, int y) {
		super(x, y, ID.Coin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 4, 8);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,4,8);
	}

}
