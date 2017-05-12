package com.player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Missile {
	private int x,y,angle,distance,speed;
	private Player p1;
	public Missile(int x, int y,Player p1,int a ){
		this.x=x;
		this.y=y;
		this.p1 = p1;
		angle = a;
		speed=30;
	}
	public Missile(int x, int y,Player p1,int a,int s ){
		this.x=x;
		this.y=y;
		this.p1 = p1;
		angle = a;
		speed=s;
	}
	public Rectangle getShape(){
		return new Rectangle(x,y-5,5,5);
	}
	public void draw(Graphics g){
		/*Graphics2D g2 = (Graphics2D) g; 
        Shape r = new Rectangle(x,y-10,5,10);
        r=AffineTransform.getRotateInstance(Math.toRadians(angle), r.getBounds2D().getCenterX(), r.getBounds2D().getCenterY()).createTransformedShape(r);
        g2.draw(r);*/
        g.drawOval(x, y-5, 5, 5);
	}
	public void move(){
		
		x+= speed*Math.sin(Math.toRadians(angle));
		y-= speed*Math.cos(Math.toRadians(angle));
		distance += (int) Math.sqrt(speed*Math.sin(Math.toRadians(angle))*speed*Math.sin(Math.toRadians(angle)) + speed*Math.cos(Math.toRadians(angle))*speed*Math.cos(Math.toRadians(angle)));
		//System.out.println(distance);
		/*int tempAngle = Math.abs(angle);
		tempAngle %=360;
		if(tempAngle>=0&&tempAngle<90){
			double newTemp = (360-tempAngle)/360;
			x+=10;
			y-=10*newTemp;
			System.out.println(x+" "+y);
		}*/
		//System.out.println(x+" "+y);
		if(x<0){
			x=Panel.width;
		}
		else if(x>Panel.width){
			x=0;
		}
		if(y<0){
			y=Panel.height;
		}
		else if(y>Panel.height){
			y=0;
		}
	}
	public int getY(){
		return y;
	}
	public int getX(){
		return y;
	}
	public boolean isDead(){
		//System.out.println(distance > Panel.width*3);
		return distance > Panel.width;
		//return x>Panel.width||x<0||y<0||y>Panel.height;
	}
}
