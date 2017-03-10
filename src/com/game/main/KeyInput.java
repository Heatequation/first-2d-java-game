package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		this.keyDown = new boolean[]{false, false, false, false};
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);
		
		for (GameObject o: handler.objects) {
			if (o.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {o.setVelY(-5); keyDown[0] = true;}
				if (key == KeyEvent.VK_S) {o.setVelY(5); keyDown[1] = true;}
				if (key == KeyEvent.VK_A) {o.setVelX(-5); keyDown[2] = true;}
				if (key == KeyEvent.VK_D) {o.setVelX(5); keyDown[3] = true;}
			}
		}
		

		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for (GameObject o: handler.objects) {
			if (o.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) keyDown[0] = false; //o.setVelY(0);
				if (key == KeyEvent.VK_S) keyDown[1] = false; //o.setVelY(0);
				if (key == KeyEvent.VK_A) keyDown[2] = false; //o.setVelX(0);
				if (key == KeyEvent.VK_D) keyDown[3] = false; //o.setVelX(0);
				
				if (!keyDown[0] && !keyDown[1]) o.setVelY(0);
				if (!keyDown[2] && !keyDown[3]) o.setVelX(0);
			}
		}
	}
}
