package Spectator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.player.Asteroid;
import com.player.Drawer;
import com.player.LargeAsteroid;
import com.player.MediumAsteroid;
import com.player.Missile;
import com.player.Player;
import com.player.SmallAsteroid;
import com.player.Transmitter;
import com.player.keyControl;


public class Graphic extends JPanel{
	private javax.swing.Timer timer;
	public static int width, height;
	public static Shape s;

	public Graphic(int width, int height) {
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.width = width;
		this.height = height;
		timer = new javax.swing.Timer(40, new TimerListener());
		timer.start();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		
		Shape s = new Triangle(Spectator.player.getX(), Spectator.player.getY(), Spectator.player.getX()-15, Spectator.player.getY()+30, Spectator.player.getX()+15, Spectator.player.getY()+30);
		 s = AffineTransform.getRotateInstance(Math.toRadians(Spectator.player.getAngle()), s.getBounds2D().getCenterX(),
				s.getBounds2D().getCenterY()).createTransformedShape(s);
		g2.draw(s);
		for(SpecBul b: Spectator.bullets){
			//g.drawOval(b.getX(), b.getY(), 5, 5);
		}
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			repaint();
		}
	}

	

	private static class Triangle extends Polygon {
		public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
			super(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
		}
	}
}
