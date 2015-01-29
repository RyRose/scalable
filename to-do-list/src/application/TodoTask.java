package application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class TodoTask extends HBox {
	
	String taskText;
	TextField field;
	
	public TodoTask() {
		taskText = "";
		setUpInitialLayout();
	}
	
	private void setUpInitialLayout() {
		setUpTextField();
	}
	
	private void setUpTextField() {
		field = new TextField();
		getChildren().add( field );
		
		field.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) { onEnterPressed(event);	}
		});
		
		// idea taken from here: http://stackoverflow.com/questions/12971771/javafx-2-setting-the-caret-position-in-the-table-view-edit-textfield-after-reque
		// The textField wouldn't request focus normally so this delays the request for focus to where it is a possible.
		Platform.runLater( 
                new Runnable() {
                    @Override 
                    public void run() { 
                        field.requestFocus(); 
                    } 
                }); 
	}
	
	private void onEnterPressed( KeyEvent event ) {
		if ( event.getCode() == KeyCode.ENTER) {
			taskText = field.getText();
			getChildren().clear();
			getChildren().add( getPrettyLabel() );
		}
	}
	
	private Label getPrettyLabel() {
		Label label = new Label( "  " + taskText);
		return label;
	}
}
