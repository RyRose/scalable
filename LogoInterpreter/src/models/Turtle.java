package models;

public class Turtle {
	private double newX; private double newY;
	private double oldX; private double oldY;
	private final double homeX; private final double homeY;
	private int direction;
	
	
	public Turtle(int x, int y, int direction) {
		oldX = newX = homeX = x;
		oldY = newY = homeY = y;
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
		newX += units * Math.cos( Math.toRadians(direction) );
		newY += units * Math.sin( Math.toRadians(direction) );
	}
	
	public void moveHome() {
		oldX = newX;
		oldY = newY;
		newX = homeX;
		newY = homeY;
	}
	
	public void rotateRight( int degrees ) {
		direction += degrees;
	}
	
	public void rotateLeft( int degrees ) {
		direction -= degrees;
	}
	
	public void reset() {
		direction = 0;
		moveHome();
	}
}
