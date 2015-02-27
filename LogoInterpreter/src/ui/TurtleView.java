package ui;

import models.Speed;
import models.Turtle;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class TurtleView extends Polygon{
	
	Turtle turtle;
	SequentialTransition transition;
	
	private boolean isPendown;
	
	public TurtleView(Turtle turtle) {
		super( turtle.getCoordinates()[0] + 10, turtle.getCoordinates()[1], 
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] + 5,
			   turtle.getCoordinates()[0] - 10, turtle.getCoordinates()[1] - 5);
		this.turtle = turtle;
		transition = new SequentialTransition();
		isPendown = true;
	}
	
	public void setPendown( boolean bool ) {
		isPendown = bool;
	}
	
	public boolean isPendown() {
		return isPendown;
	}
	
	public void move( int units, Pane canvas) {
		KeyFrame keyframe;
		Timeline timeline = new Timeline();
		
		turtle.moveForward(units);
		
		Line line = new Line();
		line.setStartX( turtle.getPreviousCoordinates()[0] );
		line.setStartY( turtle.getPreviousCoordinates()[1] );
		line.setEndX( turtle.getPreviousCoordinates()[0] );
		line.setEndY( turtle.getPreviousCoordinates()[1] );
		
		if (isPendown) {
			canvas.getChildren().add(0, line);
			keyframe = new KeyFrame( Duration.millis(getSpeed()), 
					new KeyValue(translateXProperty(), turtle.getCoordinates()[0] - 275),
					new KeyValue(translateYProperty(), turtle.getCoordinates()[1] - 275),
					new KeyValue(line.endXProperty(), turtle.getCoordinates()[0]),
					new KeyValue(line.endYProperty(), turtle.getCoordinates()[1]) );
		} else {
			keyframe = new KeyFrame( Duration.millis(getSpeed()), 
					new KeyValue(translateXProperty(), turtle.getCoordinates()[0] - 275),
					new KeyValue(translateYProperty(), turtle.getCoordinates()[1] - 275));
		}

		timeline.getKeyFrames().add(keyframe);
		transition.getChildren().add(timeline);
	}
	
	public void rotateRight( int degrees ) {
		Timeline timeline = new Timeline();
		turtle.rotateRight(degrees);
		
		KeyFrame keyFrame = new KeyFrame( Duration.millis(getSpeed()),
				new KeyValue(rotateProperty(), turtle.getDirection()));
		
		timeline.getKeyFrames().add( keyFrame);
		transition.getChildren().add(timeline);
	}
	
	public void rotateLeft( int degrees ) {
		Timeline timeline = new Timeline();
		
		turtle.rotateLeft(degrees);
		
		KeyFrame keyFrame = new KeyFrame( Duration.millis(getSpeed()),
				new KeyValue(rotateProperty(), turtle.getDirection()));
		
		timeline.getKeyFrames().add(keyFrame);
		transition.getChildren().add(timeline);
		transition.play();
	}
	
	public void goHome( Pane canvas ) {
		KeyFrame keyframe;
		Timeline timeline = new Timeline();
		turtle.moveHome();
		
		Line line = new Line();
		line.setStartX( turtle.getPreviousCoordinates()[0] );
		line.setStartY( turtle.getPreviousCoordinates()[1] );
		line.setEndX( turtle.getPreviousCoordinates()[0] );
		line.setEndY( turtle.getPreviousCoordinates()[1] );

		
		if (isPendown) {
			canvas.getChildren().add(0, line);
			keyframe = new KeyFrame( Duration.millis(getSpeed()), 
					new KeyValue(translateXProperty(), turtle.getHomeCoordinates()[0] - 275),
					new KeyValue(translateYProperty(), turtle.getHomeCoordinates()[1] - 275),
					new KeyValue(line.endXProperty(), turtle.getHomeCoordinates()[0]),
					new KeyValue(line.endYProperty(), turtle.getHomeCoordinates()[1]) );
		} else {
			keyframe = new KeyFrame( Duration.millis(getSpeed()), 
					new KeyValue(translateXProperty(), turtle.getHomeCoordinates()[0] - 275),
					new KeyValue(translateYProperty(), turtle.getHomeCoordinates()[1] - 275));
		}

		timeline.getKeyFrames().add(keyframe);
		transition.getChildren().add(timeline);		
	}
	
	public void reset() {
		turtle.reset();
		isPendown = true;
		transition.getChildren().clear();
		setVisible(true);
		setRotate(0);
		setTranslateX(turtle.getHomeCoordinates()[0] - 275 );
		setTranslateY(turtle.getHomeCoordinates()[1] - 275 );
	}
	
	public void setSpeed( Speed speed ) {
		turtle.speed = speed;
	}

	private double getSpeed() {
		switch(turtle.speed) {
		case SLOW:
			return 1000;
		case MEDIUM:
			return 100;
		case FAST:
			return 10;
		case INSTANT:
			return 1;
		default:
			throw new IllegalArgumentException("Enum not valid");
		}
	}
}
