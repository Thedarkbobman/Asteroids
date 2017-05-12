package TwoPlayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private javax.swing.Timer timer, asteroidTimer;
	private Main drawer;
	public static Player p1,p2;
	public ArrayList<Asteroid> asteroids;
	public static ArrayList<Missile> missiles,missiles2;
	public static int width, height;
	public static Shape s,s2;

	public Panel(int width, int height, Main drawer) {
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.width = width;
		this.height = height;
		p1 = new Player(width / 2-100, height / 2);
		p2 = new Player(width / 2+100, height / 2);
		this.drawer = drawer;
		asteroids = new ArrayList<Asteroid>();
		missiles = new ArrayList<Missile>();
		missiles2 = new ArrayList<Missile>();
		timer = new javax.swing.Timer(40, new TimerListener());
		timer.start();
		asteroidTimer = new javax.swing.Timer(5000, new AsteroidListener());
		asteroidTimer.start();
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//p1
		if (p1.isInvulnerable()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
	
		Graphics2D g2 = (Graphics2D) g;
		s = new Triangle(p1.xVals()[0], p1.yVals()[0], p1.xVals()[1], p1.yVals()[1], p1.xVals()[2], p1.yVals()[2]);
		s = AffineTransform.getRotateInstance(Math.toRadians(p1.getAngle()), s.getBounds2D().getCenterX(),
				s.getBounds2D().getCenterY()).createTransformedShape(s);
		g2.draw(s);

		if(p1.isInvulnerable()){
			g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		else{
		g.setColor(Color.GREEN);
		}
		Shape pointer = new Triangle(p1.xVals()[0], p1.yVals()[0], p1.xVals()[1]+8, p1.yVals()[1], p1.xVals()[2]-8, p1.yVals()[2]);
		pointer = AffineTransform.getRotateInstance(Math.toRadians(p1.getAngle()), pointer.getBounds2D().getCenterX(),
				pointer.getBounds2D().getCenterY()).createTransformedShape(pointer);
		g2.draw(pointer);
		
		//p2
		if (p2.isInvulnerable()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
	
		s2 = new Triangle(p2.xVals()[0], p2.yVals()[0], p2.xVals()[1], p2.yVals()[1], p2.xVals()[2], p2.yVals()[2]);
		s2 = AffineTransform.getRotateInstance(Math.toRadians(p2.getAngle()), s2.getBounds2D().getCenterX(),
				s2.getBounds2D().getCenterY()).createTransformedShape(s2);
		g2.draw(s2);

		if(p2.isInvulnerable()){
			g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		else{
		g.setColor(Color.GREEN);
		}
		Shape pointer2 = new Triangle(p2.xVals()[0], p2.yVals()[0], p2.xVals()[1]+8, p2.yVals()[1], p2.xVals()[2]-8, p2.yVals()[2]);
		pointer2 = AffineTransform.getRotateInstance(Math.toRadians(p2.getAngle()), pointer2.getBounds2D().getCenterX(),
				pointer2.getBounds2D().getCenterY()).createTransformedShape(pointer2);
		g2.draw(pointer2);
		
		
		
		
		g.setColor(Color.WHITE);
		for (Asteroid a : asteroids) {
			a.draw(g);
		}
		for (Missile m : missiles) {
			m.draw(g);
		}
		for (Missile m : missiles2) {
			m.draw(g);
		}
		
		
		
		
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		g.drawString("Score:" + p1.getScore(), 50, 50);
		g.drawString("Deaths:" + p1.getDeaths(), width - 800, 50);
		if(keyControl.canSpecFire){
			g.drawString("Surround Shot: Charged", width - 500, 50);
		}
		else{
		g.drawString("Surround Shot: " + (10-(keyControl.endSpec-keyControl.startSpec)/1000)+" seconds left", width - 500, 50);
		}
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Main.keyControl.tick();
			for (Asteroid a : asteroids) {
				a.move();
			}

			for (int i = missiles.size() - 1; i >= 0; i--) {
				/*
				 * missiles.get(i).move(); int x = missiles.get(i).getX(); int y
				 * = missiles.get(i).getY(); //System.out.println(x+" "+y);
				 * if(x>width||x<0){ missiles.remove(i); } else
				 * if(y>height||y<0){ missiles.remove(i); }
				 */
				missiles.get(i).move();
				// System.out.println(missiles.get(i).isDead());
				if (missiles.get(i).isDead()) {
					missiles.remove(i);
					p1.descreaseShot();
				}

			}

			if (missiles.size() > 0) {
				int max = missiles.size();
				goHere: for (int i = 0; i < max; i++) {
					int max2 = asteroids.size();
					max = missiles.size();
					missiles.get(i).move();
					for (int k = 0; k < max2; k++) {

						if (missiles.size() > i || asteroids.size() > k) {
							// System.out.println(i + " " + k);
							if (asteroids.get(k).hit(missiles.get(i).getShape()) && i < max) {
								missiles.remove(i);
								p1.descreaseShot();
								max2 = asteroids.size();
								max = missiles.size();
								if (asteroids.get(k) instanceof MediumAsteroid) {
									Asteroid[] asteroidArray = ((MediumAsteroid) asteroids.get(k)).split();
									for (Asteroid newA : asteroidArray) {
										asteroids.add(newA);
									}
								}
								if (asteroids.get(k) instanceof LargeAsteroid) {
									Asteroid[] asteroidArray = ((LargeAsteroid) asteroids.get(k)).split();
									for (Asteroid newA : asteroidArray) {
										asteroids.add(newA);
									}
								}
								if (asteroids.get(k) instanceof SmallAsteroid) {
									
								}
								p1.scored();
								asteroids.remove(k);
								break goHere;
							}
						}
					}

				}
			}
			for (int i = asteroids.size() - 1; i >= 0; i--) {
				
				if (s.intersects(asteroids.get(i).x - asteroids.get(i).getWidth() / 2,
						asteroids.get(i).y - asteroids.get(i).getWidth() / 2, asteroids.get(i).getWidth(),
						asteroids.get(i).getWidth())) {
					if(!p1.isInvulnerable()){
					if (asteroids.get(i) instanceof MediumAsteroid) {
						Asteroid[] asteroidArray = ((MediumAsteroid) asteroids.get(i)).split();
						for (Asteroid newA : asteroidArray) {
							asteroids.add(newA);
						}
					}
					if (asteroids.get(i) instanceof LargeAsteroid) {
						Asteroid[] asteroidArray = ((LargeAsteroid) asteroids.get(i)).split();
						for (Asteroid newA : asteroidArray) {
							asteroids.add(newA);
						}
					}
					
					asteroids.remove(i);
					}
					p1.died();
				}
			}
			repaint();
		}
	}

	private class AsteroidListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Asteroid a = null;
			double x = Math.random();
			int xPos = (int) (Math.random() * width);
			int yPos = (int) (Math.random() * height);
			if (x < 0.1) {
				a = new LargeAsteroid(xPos, yPos);
			} else if (x < 0.5) {
				a = new MediumAsteroid(xPos, yPos);
			} else {
				a = new SmallAsteroid(xPos, yPos);
			}

			asteroids.add(a);
			asteroidTimer.setDelay(asteroidTimer.getDelay()-10);
		}

	}

	private static class Triangle extends Polygon {
		public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
			super(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
		}
	}
}
