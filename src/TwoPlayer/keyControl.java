package TwoPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class keyControl extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int width, height;
	private boolean[] keys = new boolean[65536];
	private int timerStuff = 100;
	public static boolean canSpecFire;
	public static long startSpec, endSpec;
	
	public keyControl(int w, int h,Player p) {
		width = w;
		height = h;
		canSpecFire = true;
		timer.setRepeats(false); // Only execute once

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void tick() {
		
		if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
			Panel.p1.rotateLeft();
		}

		if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
			Panel.p1.rotateRight();
		}
		if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
			Panel.p1.thrust();
		} else {
			Panel.p1.unThrust();
			if (Panel.p1.speed > 0) {
				Panel.p1.speed -= 0.2;

			} else {
				Panel.p1.speed = 0;
			}
			Panel.p1.thrustHelper();
		}
		if (keys[KeyEvent.VK_SPACE]) {
			if (Panel.p1.getManuallyShot() < 5) {
				if (Panel.p1.getAngle() == 90) {
					Panel.missiles
							.add(new Missile(Panel.p1.xCent() + 15, Panel.p1.yCent(), Panel.p1, Panel.p1.getAngle()));
				} else if (Panel.p1.getAngle() == 270) {
					Panel.missiles
							.add(new Missile(Panel.p1.xCent() - 15, Panel.p1.yCent(), Panel.p1, Panel.p1.getAngle()));
				} else {
					Panel.missiles
							.add(new Missile(Panel.p1.xCent(), Panel.p1.yCent() - 15, Panel.p1, Panel.p1.getAngle()));
				}
				Panel.p1.shot();
			}
		}
		if (keys[KeyEvent.VK_C] && canSpecFire) {
			for (int i = 0; i < 30; i++) {
				Panel.missiles.add(new Missile(Panel.p1.xCent(), Panel.p1.yCent() - 15, Panel.p1, i * 12, 20));
				canSpecFire = false;
				startSpec = System.currentTimeMillis();
				timer.start();

			}
		}
		endSpec = System.currentTimeMillis();
	}

	Timer timer = new Timer(10000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			canSpecFire = true;
			startSpec = 0;
			endSpec = 0;
		}
	});

}
