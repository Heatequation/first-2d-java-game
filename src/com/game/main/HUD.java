package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	
	public HUD() {
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 20);
		g.setColor(Color.green);
		g.fillRect(5, 5, Spawner.health*2, 20);
		g.setColor(Color.white);
		g.drawRect(5, 5, 200, 20);
		
		g.drawString("Score: " + Spawner.score, 10, 64);
		g.drawString("Level: " + Spawner.level, 10, 80);
	}
	

}
