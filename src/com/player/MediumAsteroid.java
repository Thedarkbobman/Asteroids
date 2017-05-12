package com.player;

import java.awt.Rectangle;

public class MediumAsteroid extends Asteroid{

	public MediumAsteroid(int x, int y) {
		super(x, y,100,2);
	}
	public MediumAsteroid(int x, int y, boolean b) {
		super(x, y,100,2,b);
	}
	public boolean hit(Rectangle r){
		Rectangle me = new Rectangle(x-50,y-50,100,100);
		//System.out.println(me.intersects(r));
		return me.intersects(r);
	}
	public SmallAsteroid[] split(){
		SmallAsteroid[] asteroids = new SmallAsteroid[3];
		asteroids[0] = new SmallAsteroid(x+(int)(Math.random()*20), y,true);
		asteroids[1] = new SmallAsteroid(x+(int)(Math.random()*20), y,true);
		asteroids[2] = new SmallAsteroid(x+(int)(Math.random()*20), y,true);
		return asteroids;
	}
}
