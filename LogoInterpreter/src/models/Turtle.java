package models;

public class Turtle {
	private double newX;
	private double newY;
	private double oldX;
	private double oldY;
	private int direction;
	
	public Turtle(int x, int y, int direction) {
		oldX = newX = x;
		oldY = newY = y;
		this.direction = direction;
	}
	
	public double[] getCoordinates() {
		return new double[] {newX, newY};
	}
	
	public double[] getPreviousCoordinates() {
		return new double[] {oldX, oldY};
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void move( int units ) {
		oldX = newX;
		oldY = newY;
		newX += units * Math.cos(direction);
		newY += units * Math.sin(direction);
	}
	
	public void rotateRight( int degrees ) {
		direction -= degrees;
	}
	
	public void rotateLeft( int degrees ) {
		direction += degrees;
	}
}
