package ui;

import models.Model;
import models.Turtle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Controller {
	
	Model model;
	
	@FXML
	Pane canvas;
	
	@FXML
	Button submit;
	
	@FXML
	TextArea textEntry;
	
	TurtleView turtleView;

	@FXML
	void initialize() {
		Turtle turtle = new Turtle( 300, 300, 0);
		turtleView = new TurtleView(turtle);
		canvas.getChildren().add(turtleView);
	}
	
	@FXML
	void onSubmitInstructions() {
		System.out.println("hello");
	}
}
