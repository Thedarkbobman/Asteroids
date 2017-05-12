import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import com.player.Asteroid;
import com.player.Missile;
import com.player.Player;
import com.player.SmallAsteroid;
import com.player.MediumAsteroid;
import com.player.LargeAsteroid;
public class Server {
	static Player p1, p2;
	static ArrayList<Missile> missiles1, missiles2;
	static ArrayList<Asteroid> asteroids;
	public static void main(String[] args) throws IOException {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth()-100;
		int height = gd.getDisplayMode().getHeight()-100;
		 p1 = new Player(width/2-100, height/2);
		 p2 = new Player(width/2+100, height/2);
		 missiles1 = new ArrayList<Missile>();
		 missiles2 = new ArrayList<Missile>();
		 asteroids = new ArrayList<Asteroid>();
		DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
           {
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              serverSocket.receive(receivePacket);
              String sentence = new String( receivePacket.getData());
              parseInput(sentence);
              InetAddress IPAddress = receivePacket.getAddress();
              int port = receivePacket.getPort();
              String capitalizedSentence = "";
              sendData = capitalizedSentence.getBytes();
              DatagramPacket sendPacket =
              new DatagramPacket(sendData, sendData.length, IPAddress, port);
              serverSocket.send(sendPacket);
           }
	}
	public static void parseInput(String s){
		//sample
		//{p1:x,y;p2:x,y}
		
		int x = 0;
		int y = 0;
		String temp = s.substring(3);
		p1.setX(x, x-15, x+15);
		p1.setX(y, y-15, y+15);
	}
}
