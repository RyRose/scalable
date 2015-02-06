package ui;

import models.TodoTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LeftTodoTaskView extends HBox {
	
	private final String DONE_BUTTON_TEXT = "DONE";
	
	TodoTaskView callback;
	
	private TextField field;
	private Text taskTextView;
	private Button doneButton;
	private Button deleteButton;
	
	TodoTask task;

	public LeftTodoTaskView( TodoTaskView callback, TodoTask task ) {
		this.callback = callback;
		this.task = task;
		setUpLayout();
	}
	
	private void setUpLayout() {
		setAlignment(Pos.CENTER_LEFT);
		taskTextView = new Text();
		setUpDeleteButton();
		setUpDoubleClick();
		setUpDoneButton();
		
		if( hasPreviousState() ) {
			getChildren().add(doneButton);
			addLabel();
			if ( !task.isEnabled() ) {
				disable();
			}
		} else {
			setUpTextField();
		}
	}
	
	private boolean hasPreviousState() {
		return !task.getMainText().isEmpty();
	}
	
	private void setUpDeleteButton() {
		deleteButton = new Button();
		deleteButton.setText("X");
		deleteButton.addEventHandler( ActionEvent.ACTION, event -> callback.onDeleteItem() );
	}
	
	private void setUpDoubleClick() {
		callback.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onDoubleClick(event));
	}
	
	private void onDoubleClick( MouseEvent event ) {
		if ( event.getClickCount() == 2)
			onTaskEnableDisable();
	}
	
	private void setUpDoneButton() {
		doneButton = new Button();
		doneButton.setText(DONE_BUTTON_TEXT);
		doneButton.addEventHandler(ActionEvent.ACTION, event -> onTaskEnableDisable());
	}
	
	private void onTaskEnableDisable() {
		if (task.isEnabled()) 
			disable();
		else 
			enable();
		
	}
	
	private void enable() {
		task.enable();
		taskTextView.strikethroughProperty().set(false);
		if ( getChildren().contains(deleteButton) )
			getChildren().remove(deleteButton);
	}
	
	private void disable() {
		task.disable();
		taskTextView.strikethroughProperty().set(true);
		getChildren().add(deleteButton);
	}
	
	private void setUpTextField() {
		field = new TextField();
		field.focusedProperty().addListener( (observable, oldvalue, newValue) -> onTextFieldInterrupt( !newValue ) );
		field.addEventHandler(KeyEvent.KEY_PRESSED, event -> onEnterPressed(event) );
		Platform.runLater( () -> field.requestFocus() );
		getChildren().add(field);
	}
	
	private void onTextFieldInterrupt( Boolean isFocusReleased ) {
		if ( isFocusReleased && field.getText().isEmpty()) 
			callback.onTextFieldInterrupt();;
	}
	
	private void onEnterPressed( KeyEvent event ) {
		if ( event.getCode() == KeyCode.ENTER) {
			task.setText( field.getText() );
			getChildren().remove(field);
			getChildren().add(doneButton);
			addLabel();
		}
	}
		
	private void addLabel() {
		taskTextView.setText("   " + task.getMainText() + "   ");
		getChildren().add(taskTextView);
	}
}
