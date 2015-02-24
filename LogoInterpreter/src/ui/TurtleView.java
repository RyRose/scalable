package ui;

import models.Turtle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class TurtleView extends Polygon{
	
	
	Turtle turtle;
	Pane canvas;
	
	private boolean isPendown;
	
	public TurtleView(Turtle turtle, Pane canvas) {
		super( turtle.getCoordinates()[0] + 10, turtle.getCoordinates()[1], 
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] + 5,
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] - 5);
		this.turtle = turtle;
		isPendown = true;
		this.canvas = canvas;
	}
	
	public void move( int units, Pane canvas) {
		turtle.move(units);
		if (isPendown) 
			drawPath( canvas );
		setTranslateX(turtle.getCoordinates()[0] - 275);
		setTranslateY(turtle.getCoordinates()[1] - 275);
	}
	
	private void drawPath( Pane canvas ) {
		Line line = new Line();
		line.setStartX(turtle.getPreviousCoordinates()[0]);
		line.setStartY(turtle.getPreviousCoordinates()[1]);
		line.setEndX(turtle.getCoordinates()[0]);
		line.setEndY(turtle.getCoordinates()[1]);
		canvas.getChildren().add(0, line);
	}
	
	public void rotateRight( int degrees ) {
		turtle.rotateRight(degrees);
		setRotate(turtle.getDirection());
	}
	
	public void rotateLeft( int degrees ) {
		turtle.rotateLeft(degrees);
		setRotate(turtle.getDirection());
	}
	
	public void goHome( Pane canvas ) {
		turtle.moveHome();
		
		if( isPendown )
			drawPath(canvas);
		
		setTranslateX(turtle.getCoordinates()[0] - 300);
		setTranslateY(turtle.getCoordinates()[1] - 300);
	}
	
	public void setPendown( boolean bool ) {
		isPendown = bool;
	}
	
	public void reset() {
		turtle.reset();
		setPendown(true);
		setVisible(true);
		setRotate(0);
		setTranslateX(turtle.getCoordinates()[0] - 300);
		setTranslateY(turtle.getCoordinates()[1] - 300);
	}
}
