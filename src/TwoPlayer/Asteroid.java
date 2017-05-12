package TwoPlayer;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Asteroid {
	protected int x;
	protected int y;
	private int b;
	private double slope;
	private int width;
	private int delX;
	private boolean isDed;
	public Asteroid(int x, int y, int w,int s) {
		delX=s;
		slope = Math.random() * 10 - 5;
		if(Math.random()>0.5){
			delX*=-1;
			x=Panel.width;
		}
		else{
			x=0;
		}
		
			y=(int)(Math.random()*Panel.height);
		
		this.x = x;
		this.y = y;
		b = y;
		width = w;
		
		isDed=false;
		
	}
	public Asteroid(int x, int y, int w,int s,boolean t){
		delX=s;
		slope = Math.random() * 10 - 5;
		if(Math.random()>0.5){
			delX*=-1;
		}

		
		this.x = x;
		this.y = y;
		b = y;
		width = w;
		
		isDed=false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {
		x += delX;
		y += 2 * slope;
		if (x < -width / 2) {
			x = Panel.width + width / 2;

		} else if (x > Panel.width + width / 2) {
			x = -1 * width / 2;
		}
		if (y < -width / 2) {
			y = Panel.height + width / 2;
		} else if (y > Panel.height + width / 2) {
			y = -1 * width / 2;
		}
		// System.out.println(x+" "+y);
	}

	public int getWidth() {
		return width;
	}

	public void draw(Graphics g) {
		g.drawOval(x - width / 2, y - width / 2, width, width);
		//g.drawRect(x-width/2, y-width/2, width, width);
	}
	protected void kill(){
		isDed=true;
	}
	public boolean isDead(){
		return isDed;
	}
	public abstract boolean hit(Rectangle r);
}
