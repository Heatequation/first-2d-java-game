package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	List<GameObject> objects = new LinkedList<GameObject>();
	List<GameObject> addList = new LinkedList<GameObject>();
	List<GameObject> delList = new LinkedList<GameObject>();
	
	public void tick() {
		for (GameObject o : objects) {
			o.tick();
		}
	}
	
	public void render(Graphics g) {
		objects.stream().forEach((o) -> o.render(g));
	}
	
	public void addObject(GameObject o) {
		this.addList.add(o);
	}
	
	public void removeObject(GameObject o) {
		this.delList.add(o);
	}
	
	public void removeObjects() {
		this.objects.removeAll(delList);
		delList.clear();
	}

	public void addObjects() {
		this.objects.addAll(addList);
		addList.clear();
		
	}
}
