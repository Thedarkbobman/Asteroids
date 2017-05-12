package com.player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Transmitter {
	byte[] receiveData, sendData;
	DatagramSocket serverSocket;
	DatagramPacket sendPacket;
	public Transmitter() throws IOException{
	      serverSocket = new DatagramSocket(9876);
          receiveData = new byte[1024];
          sendData = new byte[1024];
         
	}
	public void startTransmit() throws IOException{
		sender.start();
	}
	Thread sender = new Thread() {
	    public void run() {
	    	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
	    	while(true)
	        {
	    		
	            String bullets = "";
	            for(int i = 0; i < Panel.missiles.size();i++){
	            	if(i<=Panel.missiles.size()){
	            	bullets +=Panel.missiles.get(i).getX()+","+Panel.missiles.get(i).getY()+";";
	            	}
	            }
	         /*   for(Missile m:Panel.missiles){
	            	bullets +=m.getX()+","+m.getY()+";";
	            }*/
	            bullets+="*";
	            String asteriods = "";
	            
	          /*  for(Asteroid a:Panel.asteroids){
	            		asteriods+=a.getX()+","+a.getY()+","+a.getWidth()+";";
	            	
	            }*/
	            asteriods+="*";
	              String data = "p1{x:"+Panel.p1.xVals()[0]+",y:"+Panel.p1.yVals()[0]+","+Panel.p1.isInvulnerable()+",ang:"+Panel.p1.getAngle()+"}"+"b{"+bullets+"}"+"a{"+asteriods+"}";
	              sendData = data.getBytes();
	               sendPacket =
	              new DatagramPacket(sendData, sendData.length, IPAddress, port);
	              try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         
	        }
	    }  
	};

}
