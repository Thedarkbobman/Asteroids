package Spectator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//FROM SERVER:p1{x:670y:400,false}b{*}a{652,348,100;728,580,50;1094,627,50;*}
public class Spectator extends JFrame{
	static DatagramPacket receivePacket;
	static DatagramSocket clientSocket;
	static byte[] sendData,receiveData;
	public static SpecPlay player;
	public static ArrayList<SpecBul> bullets;
	public static void main(String[] args) throws IOException {
		player = new SpecPlay();
		bullets = new ArrayList<SpecBul>();
		
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		 clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		 sendData = new byte[1024];
		 receiveData = new byte[1024];
		String sentence = "client";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		reciever.start();
		
		Spectator f = new Spectator();
		f.display();
	}
	public Spectator() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth()-100;
		int height = gd.getDisplayMode().getHeight()-100;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Graphics");
	JPanel	 bg = new JPanel();
		 bg.setBackground(Color.BLACK);
		 bg.setPreferredSize(new Dimension(width, height));
		 Graphic p1 = new Graphic(width, height);
		p1.setPreferredSize(new Dimension(width, height));

		bg.add(p1);
		getContentPane().add(bg);
		pack();
		
		
	}

	
	public void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}
	//p1{x:670,y:400,false}b{*}a{652,348,100;728,580,50;1094,627,50;*}
	static Thread reciever = new Thread() {
	    public void run() {
	    	while(true){
	    	receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println(modifiedSentence);
			String temp = modifiedSentence.substring(5, modifiedSentence.indexOf("}"));
			String x = temp.substring(0,temp.indexOf(","));
			int check = temp.indexOf(":")+1;
			String y = temp.substring(check,temp.indexOf(",",check));
			String inv = temp.substring(temp.indexOf(",",check)+1,temp.indexOf(",",temp.indexOf(",",check)+1));
			String angle = temp.substring(temp.indexOf("ang")+4);
			//System.out.println(x+" "+y+" "+inv+" "+angle);
			player.setVals(Integer.parseInt(x), Integer.parseInt(y), Boolean.parseBoolean(inv),Integer.parseInt(angle));
			check = modifiedSentence.indexOf("b");
			x = modifiedSentence.substring(check+2,modifiedSentence.indexOf("}",check));
			//System.out.println(x);
			//580,580;580,580;610,610;664,664;772,772;85,85;181,181;309,309;469,469;597,597;725,725;13,13;112,112;180,180;209,209;240,240;209,209;180,180;112,112;715,715;591,591;467,467;312,312;188,188;95,95;667,667;640,640;*
			int start = 0;
			int current = x.indexOf(";");
			while(current !=-1){
				String a = x.substring(start, current);
				x = a.substring(0, a.indexOf(","));
				y = a.substring( a.indexOf(",")+1);
				bullets.add(new SpecBul(Integer.parseInt(x),Integer.parseInt(y)));
				start = current+1;
				current = x.indexOf(";");
			}

	    }  
	    }
	};
}
