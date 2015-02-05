package ui;

import interfaces.ControllerCallback;
import interfaces.TodoListItem;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

public abstract class BaseTodoListItemView extends BorderPane {
	private final String DUMMY_STRING = "abcd";
	
	ControllerCallback callback;
	
	public BaseTodoListItemView(ControllerCallback callback) {
		this.callback = callback;
		setUpDragAndDrop();
	}
	
	private void setUpDragAndDrop() {
		setOnDragExited(event -> onDragExited(event) );
		setOnDragDetected( event -> onDragDetected(event) );
		setOnDragEntered( event -> onDragEntered(event));
		setOnDragDropped( event -> onDragDropped(event));
		setOnDragOver( event -> onDragOver(event));
	}
	
	
	private void onDragExited(DragEvent event) {
		setTop(null);
		event.consume();
	}
	
	private void onDragDetected(MouseEvent event) {
		Dragboard db = startDragAndDrop(TransferMode.ANY);
		ClipboardContent contents = new ClipboardContent();
		contents.putString(DUMMY_STRING);
		db.setContent(contents);
		event.consume();
	}
	
	private void onDragEntered( DragEvent event ) {
		if ( event.getGestureSource() != this ) {
			Line line = new Line();
			line.setStrokeWidth(3.0);
			line.setScaleX(getWidth());
			line.autosize();
			setTop(line);
			setAlignment(line, Pos.CENTER);
		}
		event.consume();
	}
	
	private void onDragDropped( DragEvent event) {
        event.setDropCompleted(true);
        TodoListItem to = (TodoListItem) event.getSource();
        TodoListItem from = (TodoListItem) event.getGestureSource();
        callback.onItemMove(from, to);
		event.consume();
	}
	
	private void onDragOver( DragEvent event ) {
		if ( event.getGestureSource() != this )
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	}
}
