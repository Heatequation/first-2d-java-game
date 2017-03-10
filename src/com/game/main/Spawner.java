package com.game.main;

import java.util.Random;

public class Spawner {

	private Random r = new Random();
	private Handler handler;
	
	public static int health = 100;
	public static int score = 0;
	private static int counter = 1;
	public static int level = 1;
	
	public Spawner(Handler handler) {
		this.handler = handler;
		
	}

	
	public void tick() {
		
		if (counter % 50 == 0) {
			handler.addObject(new BasicCoin(
					r.nextInt(Game.WIDTH), 
					r.nextInt(Game.HEIGHT) 
					));			
		}
		
		if (counter == 1 && level == 1) {
			handler.addObject(new Player(
					r.nextInt(Game.WIDTH), 
					r.nextInt(Game.HEIGHT), 
					ID.Player, 
					this.handler));
			handler.addObject(
					new BasicEnemy(
							r.nextInt(Game.WIDTH), 
							r.nextInt(Game.HEIGHT),
							4,
							ID.Enemy, 
							this.handler
							));				
		}
		
		
		if (counter % 1000 == 0) {
			++level;
			
			switch(level) {
			case 1:
				break;
			case 2: 
				handler.addObject(
						new SmartEnemy(
								r.nextInt(Game.WIDTH), 
								r.nextInt(Game.HEIGHT), 
								1,
								ID.Enemy, 
								this.handler
								));
				break;
			case 3: 
				handler
					.objects
					.stream()
					.filter(o -> o.id == ID.Enemy)
					.forEach(handler::removeObject);
				handler.addObject(new Boss1(
						Game.WIDTH/2, 
						-80, 
						120,
						ID.Enemy, 
						this.handler));	
				break;
			case 4: 
				handler
					.objects
					.stream()
					.filter(o -> o.id == ID.Enemy)
					.forEach(handler::removeObject);
				for (int i = 1; i <= 2; ++i) {
					handler.addObject(
							new BasicEnemy(
									r.nextInt(Game.WIDTH), 
									r.nextInt(Game.HEIGHT),
									5,
									ID.Enemy, 
									this.handler
									));						
				}				
				break;
			case 5: 
				handler.addObject(
						new SmartEnemy(
								r.nextInt(Game.WIDTH), 
								r.nextInt(Game.HEIGHT), 
								3.5,
								ID.Enemy, 
								this.handler
								));
				break;
			case 6: 
				handler
					.objects
					.stream()
					.filter(o -> o.id == ID.Enemy)
					.forEach(handler::removeObject);
				handler.addObject(new Boss1(
						Game.WIDTH/2, 
						-80, 
						60,
						ID.Enemy, 
						this.handler));	
				break;
			case 7: 
				handler
					.objects
					.stream()
					.filter(o -> o.id == ID.Enemy)
					.forEach(handler::removeObject);
				for (int i = 1; i <= 4; ++i) {
					handler.addObject(
							new BasicEnemy(
									r.nextInt(Game.WIDTH), 
									r.nextInt(Game.HEIGHT),
									5,
									ID.Enemy, 
									this.handler
									));						
				}					
				break;
			case 8: 
				handler.addObject(
						new SmartEnemy(
								r.nextInt(Game.WIDTH), 
								r.nextInt(Game.HEIGHT),
								4.5,
								ID.Enemy, 
								this.handler
								));
				break;
			case 9: 
				handler
					.objects
					.stream()
					.filter(o -> o.id == ID.Enemy)
					.forEach(handler::removeObject);
				handler.addObject(new Boss1(
						Game.WIDTH/2, 
						-80,
						10,
						ID.Enemy, 
						this.handler));	
				break;				
				
			}
			
		}
		
		++counter;
	}
	
	
	public static void reset() {
		Spawner.health = 100;
		Spawner.level = 1;
		Spawner.score = 0;
		Spawner.counter = 0;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getHealth() {
		return Spawner.health;
	}
}
