package models;

public class Turtle {
	public Speed speed;
	private double currentX; private double currentY;
	private double previousX; private double previousY;
	private final double homeX; private final double homeY;
	private int direction;
	
		
	public Turtle(int x, int y, int direction) {
		homeX = previousX = currentX = x;
		homeY = previousY = currentY = y;
		this.direction = direction;
		speed = Speed.MEDIUM;
	}
	
	public double[] getCoordinates() {	return new double[] {currentX, currentY}; }
	
	public double[] getPreviousCoordinates() {	return new double[] {previousX, previousY}; }
	
	public double[] getHomeCoordinates() { return new double[] {homeX, homeY}; }
	
	public int getDirection() {	return direction;	}
		
	private void move(double x, double y) {
		previousX = currentX; 
		previousY = currentY;
		currentX += x; 
		currentY += y;
	}
	
	public void moveHome() { 
		previousX = currentX;
		previousY = currentY;
		currentX = homeX;
		currentY = homeY;
	}

	public void moveForward( int units ) {
		double newX = units * Math.cos( Math.toRadians(direction) );
		double newY = units * Math.sin( Math.toRadians(direction) );
		move(newX, newY);
	}
			
	public void rotateRight( int degrees ) { this.direction += degrees; }
	
	public void rotateLeft( int degrees ) {	this.direction -= degrees; }
	
	public void reset() {
		direction = 0;
		moveHome();
	}
}
