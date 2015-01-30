package ui;

import models.Category;
import interfaces.ControllerCallback;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CategoryView extends HBox {
	
	final private double HEIGHT = 40;
	
	private Category category;
	private ControllerCallback callback;

	public CategoryView( Category category, ControllerCallback callback) {
		this.category = category;
		this.callback = callback;
		setUpLayout();
	}
	
	private void setUpLayout() {
		setMinHeight(HEIGHT);
		setAlignment( Pos.CENTER_LEFT );
		addLabel();
		addTaskButton();
	}
	
	private void addLabel() {
		Label label = new Label( category.getText() );
		getChildren().add(label);
	}
	
	private void addTaskButton() {
		Button button = new Button();
		button.setText("+");
		button.addEventHandler( MouseEvent.MOUSE_CLICKED, event -> onAddTask() );
		getChildren().add( button );
	}
	
	private void onAddTask() { 
		callback.onAddTask(this); 
	}
}
