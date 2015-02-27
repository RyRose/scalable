package ui;

import edu.hendrix.grambler.ParseException;
import models.Turtle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {
	
	
	@FXML
	Pane canvas;
	
	@FXML
	Button submit;
	
	@FXML
	Button clear;
	
	@FXML
	Button forward;
	
	@FXML
	Button back;
	
	@FXML
	Button left;
	
	@FXML
	Button right;
	
	@FXML
	Button clearScreen;
	
	@FXML
	Button penUpDown;
	
	@FXML
	Button showHideTurtle;
	
	@FXML
	TextArea textEntry;
	
	@FXML
	TextField statusBar;
	
	TurtleView turtleView;
	
	InstructionEvaluator evaluator;

	@FXML
	void initialize() {
		Turtle turtle = new Turtle( 275, 275, 0);
		turtleView = new TurtleView(turtle);
		evaluator = new InstructionEvaluator(turtleView, canvas);
		canvas.getChildren().add(turtleView);
	}
	
	@FXML
	void onSubmitInstructions() {
		statusBar.clear();
		evalInstruction(textEntry.getText());
	}
	
	private void evalInstruction( String s ) {
		turtleView.transition.getChildren().clear();
		try {
			evaluator.eval( s );
		} catch (ParseException e) {
			statusBar.appendText(e.getMessage());
		} catch (IllegalArgumentException e) {
			statusBar.appendText(e.getMessage());
		}
		turtleView.transition.play();
	}
	
	@FXML
	void clearInstructions() {
		textEntry.clear();
	}
	
	@FXML
	void moveForward() {
		evalInstruction("forward 25");
	}
	
	@FXML
	void moveBackward() {
		evalInstruction("back 25");
	}
	
	@FXML
	void rotateLeft() {
		evalInstruction("left 45");
	}
	
	@FXML
	void rotateRight() {
		evalInstruction("right 45");
	}
	
	@FXML
	void clearScreen() {
		evalInstruction("clearscreen");
	}
	
	@FXML
	void showHideTurtle() {
		if(turtleView.isVisible()) {
			evalInstruction("hideturtle");
		} else {
			evalInstruction("showturtle");
		}
	}
	
	@FXML
	void penUpDown() {
		if(turtleView.isPendown()) {
			evalInstruction("penup");
		} else {
			evalInstruction("pendown");
		}
	}
}
