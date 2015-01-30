package ui;


import models.TodoTask;
import interfaces.ControllerCallback;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TodoTaskView extends HBox {
	
	private boolean isDisabled;
	private TextField field;
	private TodoTask task;
	private Label label;
	private Button doneButton;
	private Button deleteButton;
	private ControllerCallback callback;
	
	public TodoTaskView( ControllerCallback callback, TodoTask task ) {
		this.task = task;
		this.callback = callback;
		this.label = new Label();
		isDisabled = false;
		setUpInitialLayout();
	}
	
	private void setUpInitialLayout() {
		setAlignment(Pos.CENTER_LEFT);
		setUpTextField();
		setUpDoubleClick();
	}
	
	private void setUpTextField() {
		field = new TextField();
		getChildren().add( field );
		
		field.focusedProperty().addListener( new ChangeListener<Boolean>() {
			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) { 
				onTextFieldFocusReleased( !newValue ); 
			}
			
		});
		
		field.addEventHandler(KeyEvent.KEY_PRESSED, event -> onEnterPressed(event) );
		
		// idea taken from here: http://stackoverflow.com/questions/12971771/javafx-2-setting-the-caret-position-in-the-table-view-edit-textfield-after-reque
		// The textField wouldn't request focus normally so this delays the request for focus to where it is possible.
		Platform.runLater(
                new Runnable() {
                    @Override 
                    public void run() { field.requestFocus(); } 
                }); 
	}
	
	private void onTextFieldFocusReleased( Boolean isFocusReleased ) {
		if ( isFocusReleased && field.getText().isEmpty()) 
			callback.onTaskFocusLost(this);
	}
	
	private void onEnterPressed( KeyEvent event ) {
		String fieldText = field.getText();
		
		if ( event.getCode() == KeyCode.ENTER) {
			getChildren().remove(0);
			addDoneButton();
			addLabel( fieldText );
			task.setText(fieldText);
			callback.onTaskFinished(task, this);
		}
	}
	
	private void addDoneButton() {
		doneButton = new Button();
		doneButton.setText("DONE");
		doneButton.addEventHandler(ActionEvent.ACTION, event -> onTaskEnableDisable());
		getChildren().add(0, doneButton);
	}
	
	private void onTaskEnableDisable() {
		if (isDisabled) {
			isDisabled = false;
			label.setEffect( null );
			getChildren().remove(2);
		} else {
			isDisabled = true;
			ColorAdjust effect = new ColorAdjust();
			effect.setBrightness(0.5);
			label.setEffect( effect );
			addDeleteButton();
		}
	}
	
	private void addDeleteButton() {
		deleteButton = new Button();
		deleteButton.setText("X");
		deleteButton.addEventHandler( ActionEvent.ACTION, event -> onDeleteSelf() );
		getChildren().add(deleteButton);
	}
	
	private void onDeleteSelf() {
		callback.onDeleteTask(this, task);
	}
	
	private void addLabel( String s ) {
		label.setText("   " + s + "   ");
		getChildren().add(1, label);
	}
	
	private void setUpDoubleClick() {
		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onDoubleClick(event));
	}
	
	private void onDoubleClick( MouseEvent event ) {
		if ( event.getClickCount() == 2)
			onTaskEnableDisable();
	}
}
