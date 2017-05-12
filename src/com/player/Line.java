package com.player;

import java.awt.Point;

public class Line {
	private int x1, y1, x2, y2;

	public Line(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}

	public Point getP1() {
		return new Point(x1, y1);
	}
	public Point getP2() {
		return new Point(x2, y2);
	}
}
