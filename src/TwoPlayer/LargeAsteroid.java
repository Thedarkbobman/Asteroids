package TwoPlayer;

import java.awt.Rectangle;

public class LargeAsteroid extends Asteroid {

	public LargeAsteroid(int x, int y) {
		super(x, y, 150,1);

	}

	@Override
	public boolean hit(Rectangle r) {
		Rectangle me = new Rectangle(x-75, y-75, 150, 150);
		//System.out.println(me.intersects(r));
		return me.intersects(r);
	}

	public MediumAsteroid[] split() {
		MediumAsteroid[] asteroids = new MediumAsteroid[3];
		asteroids[0] = new MediumAsteroid(x, y,true);
		asteroids[1] = new MediumAsteroid(x, y,true);
		asteroids[2] = new MediumAsteroid(x, y,true);
		return asteroids;
	}
}
