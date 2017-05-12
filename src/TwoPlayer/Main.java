package TwoPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JFrame{
	private JPanel bg;
	private Panel p1;
	public static int width,height;
	public static keyControl keyControl;
	public static void main(String[] args) {
		Main f = new Main();
		f.display();
	}

	public Main() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		 width = gd.getDisplayMode().getWidth()-100;
		 height = gd.getDisplayMode().getHeight()-100;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Graphics");
		 bg = new JPanel();
		 bg.setBackground(Color.BLACK);
		 bg.setPreferredSize(new Dimension(width, height));
		 keyControl = new keyControl(width,height,Panel.p1);
		p1 = new Panel(width, height, this);
		p1.setPreferredSize(new Dimension(width, height));

		bg.add(p1);
		getContentPane().add(bg);
		pack();
		
		this.addKeyListener(keyControl);
		
	}

	
	public void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}
}
