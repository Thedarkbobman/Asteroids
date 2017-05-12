package com.player;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SmallAsteroid extends Asteroid{

	public SmallAsteroid(int x, int y) {
		super(x, y,50,3);
	}
	public SmallAsteroid(int x, int y, boolean b) {
		super(x, y,50,3,b);
	}
	public boolean hit(Rectangle r){
		Rectangle me = new Rectangle(x-25,y-25,50,50);
		return me.intersects(r);
	}
	
}
