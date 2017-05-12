package Spectator;

public class SpecPlay {
	private int x,y,angle;
	private boolean inv;
	public SpecPlay(){
		
	}
	public void setVals(int a, int b, boolean c,int d){
		x=a;
		y=b;
		angle=d;
		inv = c;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getAngle(){
		return angle;
	}
	public boolean getINV(){
		return inv;
	}
}
