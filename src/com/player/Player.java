package com.player;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

public class Player {
	private int x, y, x2, y2, x3, y3, score, deaths;
	private int WIDTH, HEIGHT;
	private int angle;
	private Line[] lines;
	private boolean isThrusted;
	private int tX1, tY1, tX2, tY2, tX3, tY3;
	private boolean isInvul;
	public static double speed;
	private int manuallyShot;
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		WIDTH = 5;
		HEIGHT = 5;
		angle = 0;
		lines = new Line[3];
		x2 = x - 15;
		y2 = y + 30;
		x3 = x + 15;
		y3 = y + 30;
		lines[0] = new Line(x, y, x2, y2);
		lines[1] = new Line(x, y, x3, y3);
		lines[2] = new Line(x2, y2, x3, y3);
		tX1 = x2;
		tX2 = x3;
		tX3 = x;
		tY1 = y2;
		tY2 = y3;
		tY1 = y + 30;
		isInvul = false;
	}

	public Line[] getLines() {
		return lines;
	}

	public Rectangle shape() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getAngle() {
		return angle;
	}

	public void rotateLeft() {
		angle -= 15;
		// rotateHelp(false);
		// System.out.println(angle);

	}

	public void rotateRight() {
		angle += 15;
		// rotateHelp(true);
		// System.out.println(angle);

	}

	public int[] xVals() {
		return new int[] { x, x2, x3 };
	}

	public int[] yVals() {
		return new int[] { y, y2, y3 };
	}

	public int xCent() {
		return (int) ((x + x2 + x3) / 3);
	}

	public int yCent() {
		return (int) ((y + y2 + y3) / 3);
	}

	public int tXCent() {
		return (int) ((tX1 + tX2 + tX3) / 3);
	}

	public int tYCent() {
		return (int) ((tY1 + tY2 + tY3) / 3);
	}

	public void thrust() {
		if (speed < 10) {
			speed += 2;
		}
		thrustHelper();
		isThrusted = true;

	}

	public void thrustHelper() {
		double r = Math.toRadians(angle);
		x += speed * Math.sin(r);
		y -= speed * Math.cos(r);
		x2 += speed * Math.sin(r);
		y2 -= speed * Math.cos(r);
		x3 += speed * Math.sin(r);
		y3 -= speed * Math.cos(r);

		if (y < 0) {
			y2 = Panel.height;
			y3 = Panel.height;
			y = y2 - 30;
		} else if (y2 > Panel.height) {
			y2 = 30;
			y3 = 30;
			y = 0;
		}
		if (x2 < 0) {
			x3 = Panel.width;
			x2 = x3 - 30;
			x = x3 - 15;
		} else if (x3 > Panel.width) {
			x3 = 30;
			x2 = x3 - 30;
			x = x3 - 15;
		}

		setLines();
	}

	public boolean isThrust() {
		return isThrusted;
	}

	public void setLines() {
		lines[0] = new Line(x, y, x2, y2);
		lines[1] = new Line(x, y, x3, y3);
		lines[2] = new Line(x2, y2, x3, y3);
	}

	public void unThrust() {
		isThrusted = false;
	}
	public void setX(int a,int b,int c){
		x=a;
		x2=b;
		x3=c;
	}
	public void setY(int a,int b,int c){
		y=a;
		y2=b;
		y3=c;
	}
	public int[] tXVals() {
		return new int[] { tX1, tX2, tX3 };
	}

	public int[] tYVals() {
		return new int[] { tY1, tY2, tY3 };
	}

	public int getScore() {
		return score;
	}

	public void scored() {
		score += 50;
	}

	public int getDeaths() {
		return deaths;
	}

	public void died() {
		if(!isInvul){
		deaths++;
		isInvul = true;
		timer.start();
		}
		
	}
	public boolean isInvulnerable(){
		return isInvul;
	}
	public void shot(){
		manuallyShot++;
	}
	public int getManuallyShot(){
		return manuallyShot;
	}
	public void descreaseShot(){
		manuallyShot--;
		if(manuallyShot<0){
		manuallyShot=0;
		}
		System.out.println(manuallyShot);
	}
	Timer timer = new Timer(4000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			isInvul = false;
		}
	});

}
