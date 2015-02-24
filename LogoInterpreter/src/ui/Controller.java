package ui;

import models.Turtle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Controller {
	
	
	@FXML
	Pane canvas;
	
	@FXML
	Button submit;
	
	@FXML
	TextArea textEntry;
	
	TurtleView turtleView;
	
	InstructionEvaluator evaluator;

	@FXML
	void initialize() {
		Turtle turtle = new Turtle( 275, 275, 0);
		turtleView = new TurtleView(turtle, canvas);
		evaluator = new InstructionEvaluator(turtleView, canvas);
		canvas.getChildren().add(turtleView);
	}
	
	@FXML
	void onSubmitInstructions() {
		evaluator.eval( textEntry.getText()	 );
	}
}
