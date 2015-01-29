package application;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Category extends HBox {
	
	final private double HEIGHT = 40;
	
	private String name;
	private ObservableList<HBox> list;

	public Category( String s, ObservableList<HBox> l) {
		name = s + "    "; // Adding extra space between category label and button
		list = l;
		setUpHbox();
	}
	
	public String getName() {
		return name;
	}
	
	private void setUpHbox() {
		setMinHeight(HEIGHT);
		setAlignment( Pos.CENTER_LEFT );
		getChildren().add( getPrettyLabel() );
		getChildren().add( getAddTaskButton() );
	}
	
	private Label getPrettyLabel() {
		Label label = new Label( name );
		return label;
	}
	
	private Button getAddTaskButton() {
		Button button = new Button();
		button.setText("+");
		button.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {	onAddTask(event); }
		} );
		return button;
	}
	
	@SuppressWarnings("unchecked")
	private void onAddTask(ActionEvent event) {
		ListView<HBox> listView = (ListView<HBox>) getParent().getParent().getParent().getParent().getParent();
		int index = listView.getSelectionModel().getSelectedIndex();
		list.add(index + 1, new TodoTask());
	}
	
}
