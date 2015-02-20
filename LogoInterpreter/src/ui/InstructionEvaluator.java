package ui;

import parse.BaseEvaluator;
import javafx.scene.layout.Pane;

public class InstructionEvaluator extends BaseEvaluator{
		
	TurtleView turtle;
	Pane canvas;
	
	public InstructionEvaluator( TurtleView turtle, Pane canvas ) { 
		super();
		this.turtle = turtle;
		this.canvas = canvas;
	}

	@Override
	protected void handleClearscreenCommand() {
		turtle.reset();
		canvas.getChildren().clear();
		canvas.getChildren().add(turtle);
	}

	@Override
	protected void handleHomeCommand() {
		turtle.goHome(canvas);
	}

	@Override
	protected void handlePendownCommand() {
		turtle.setPendown(true);
	}

	@Override
	protected void handlePenupCommand() {
		turtle.setPendown(false);
	}

	@Override
	protected void handleHideturtleCommand() {
		turtle.setVisible(false);
	}

	@Override
	protected void handleShowturtleCommand() {
		turtle.setVisible(true);
	}

	@Override
	protected void handleMoveForwardCommand(int absolute_units) {
		turtle.move(absolute_units, canvas);
	}

	@Override
	protected void handleMoveBackwardCommand(int absolute_units) {
		turtle.move(-absolute_units, canvas);
	}

	@Override
	protected void handleRotateLeftCommand(int degrees) {
		turtle.rotateLeft(degrees);
	}

	@Override
	protected void handleRotateRightCommand(int degrees) {
		turtle.rotateRight(degrees);
		
	}
	
	
}
