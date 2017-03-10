package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.main.Game.STATE;
import com.game.main.Menu.MENUSTATE;

public class Menu extends MouseAdapter{

	public enum MENUSTATE{
		Menu,
		Help,
		GameOver
	}
	
	private Game game;
	private MENUSTATE menuState = MENUSTATE.Menu;
	
	public Menu(Game game) {
		this.game = game;
	}

	
	public void render(Graphics g) {
		
		
		if (menuState == MENUSTATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("MENU", 225, 60);
			
			g.setColor(Color.white);
			g.drawRect(200, 100, 200, 64);
			g.drawString("Play", 225, 150);
			
			g.setColor(Color.white);
			g.drawRect(200, 200, 200, 64);
			g.drawString("Help", 225, 250);
			
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Quit", 225, 350);
			
		} else if (menuState == MENUSTATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", 225, 60);
			
			g.drawString("WASD to move", 10, 150);
			g.drawString("ESC to quit", 10, 250);
			
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Back", 225, 350);
		} else if (menuState == MENUSTATE.GameOver) {
			Font fnt = new Font("arial", 1, 50);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 225, 60);
			
			g.drawString("your score: " + game.spawner.getScore(), 10, 150);
			g.drawString("your level: " + game.spawner.getLevel(), 10, 250);
			
			g.setColor(Color.white);
			g.drawRect(200, 300, 300, 64);
			g.drawString("Try again!", 225, 350);
		}
	}
	
	public void tick() {
		
	
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		game.player.soundMap.get("click").play();
		
		if (menuState == MENUSTATE.Menu) {
			if (mouseOver(mx, my, 200, 100, 200, 64)) {
				game.gameState = STATE.Game;
			}
			
			if (mouseOver(mx, my, 200, 200, 200, 64)) {
				this.menuState = MENUSTATE.Help;
			}
			
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				System.exit(0);
			}
		} else if (menuState == MENUSTATE.Help) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				this.menuState = MENUSTATE.Menu;
			}
		} else if (menuState == MENUSTATE.GameOver) {
			if (mouseOver(mx, my, 200, 300, 300, 64)) {
				this.menuState = MENUSTATE.Menu;
				this.game.gameState = STATE.Game;
				this.game.reset();
			}
		}
		

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (x < mx && mx < x + width && y < my && my < y + height) return true;
		return false;
	}


	public void setState(MENUSTATE state) {
		this.menuState = state;
		
	}
	
}
