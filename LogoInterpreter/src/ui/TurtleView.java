package ui;

import models.Turtle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class TurtleView extends Polygon{
	
	
	Turtle turtle;
	
	public TurtleView(Turtle turtle) {
		super( turtle.getCoordinates()[0] + 10, turtle.getCoordinates()[1], 
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] + 5,
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] - 5);
		this.turtle = turtle;
		System.out.println(turtle.getCoordinates()[1]);
	}
	
	public void move( int units, Pane canvas ) {
		turtle.move(units);
		Line line = new Line();
		line.setStartX(turtle.getPreviousCoordinates()[0]);
		line.setStartY(turtle.getPreviousCoordinates()[1]);
		line.setEndX(turtle.getCoordinates()[0]);
		line.setEndY(turtle.getCoordinates()[1]);
		canvas.getChildren().add(0, line);
		setTranslateX(turtle.getCoordinates()[0] - 200);
		setTranslateY(turtle.getCoordinates()[1] - 200);
	}
	
	public void rotateRight( int degrees ) {
		turtle.rotateRight(degrees);
		setRotate(turtle.getDirection());
	}
	
	public void rotateLeft( int degrees ) {
		turtle.rotateLeft(degrees);
		setRotate(turtle.getDirection());
	}
}
