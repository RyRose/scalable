package ui;

import models.Category;
import interfaces.ControllerCallback;
import interfaces.TodoListItem;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CategoryView extends BaseTodoListItemView implements TodoListItem {
	
	final private double HEIGHT = 40;
	
	private Category category;
	private ControllerCallback callback;
	
	private HBox box;

	public CategoryView( Category category, ControllerCallback callback) {
		super( callback );
		box = new HBox();
		this.category = category;
		this.callback = callback;
		setUpLayout();
	}
	
	@Override
	public String getMainText() {
		return category.getMainText();
	}
	
	private void setUpLayout() {
		setMinHeight(HEIGHT);
		setLeft(box);
		box.setAlignment( Pos.CENTER_LEFT );
		addLabel();
		addTaskButton();
	}
	
	private void addLabel() {
		Label label = new Label( category.getMainText().concat("    ") );
		box.getChildren().add(label);
	}
	
	private void addTaskButton() {
		Button button = new Button();
		button.setText("+");
		button.addEventHandler( MouseEvent.MOUSE_CLICKED, event -> callback.onAddItem(this) );
		box.getChildren().add( button );
	}
	
	@Override
	public String toString() {
		return category.toString();
	}

}
