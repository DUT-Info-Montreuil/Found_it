package application.modele.algo;

public class Coord {

	private int x;
	private int y;
	private int value;
	
	public Coord(int i , int j) {
		x = i;
		y = j;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean equal(Coord c) {
		return getX() == c.getX()&&getY() == c.getY();
	}
	public void setVal(int val) {
		value = val;
	}
	public int getVal() {
		return value;
	}
	
}
