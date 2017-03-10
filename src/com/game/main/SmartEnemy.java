package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private Player player;
	private double vel;

	public SmartEnemy(int x, int y, double vel, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub

		this.handler = handler;
		this.vel = vel;

		this.player = (Player) handler
						.objects
						.stream()
						.filter(o -> o.getID() == ID.Player)
						.findAny()
						.orElse(null);
	}

	@Override
	public void tick() {
		
		velX = 0;
		velY = 0;
		
		if (player != null) {
			int diffX = x - player.getX() - 16;
			int diffY = y - player.getY() - 16;
		
		
		double dist = Math.sqrt(Math.pow((double) diffX,2.) 
				+ Math.pow((double) diffY,2.));		

		//wenn Player velX und velY = 5 => (-3.5) => halb so schnell auf Player zu
		velX = (int) Math.round(-1 * vel / dist * (double) diffX);	
		velY = (int) Math.round(-1 * vel / dist * (double) diffY);
		
		}
		
		x += velX;
		y += velY;
		
		this.handler.addObject(
				new Trail(x,y,ID.Trail,this.handler,Color.orange,16,16)
					);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 16, 16);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
