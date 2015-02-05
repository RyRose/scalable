package application;

import java.io.File;

import models.Category;
import models.Model;
import models.TodoTask;
import ui.CategoryView;
import ui.TodoTaskView;
import interfaces.ControllerCallback;
import interfaces.TodoListItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Controller implements ControllerCallback {
	private final String[] PRESET_CATEGORIES = new String[] { "Today", "Tomorrow", "Later"};
	
	@FXML
	private ListView<TodoListItem> listView;
	
	private Model model;
	
	@FXML
	private void initialize() {
		model = new Model(this);
		if ( hasSavedState() ) {
			model.setObservableList(FileHandler.read(this));
		} else {
			addPresetCategoriesToList();
		}
		listView.setItems( model.getObservableList() );
	}
	
	private boolean hasSavedState() {
		File file = new File(FileHandler.FILE_NAME);
		return file.exists();
	}
	
	private void addPresetCategoriesToList() {	
		for ( int i = 0; i < PRESET_CATEGORIES.length; i++ ) {
			Category category = new Category( PRESET_CATEGORIES[i] );
			model.add( new CategoryView(category, this));
		}
	}
	
	public void setStageForApplicationClose(Stage stage) {
		stage.setOnCloseRequest(event -> onApplicationClose());
	}
	
	private void onApplicationClose() {
		FileHandler.write(model.getObservableList());
	}

	@Override
	public void onAddItem(TodoListItem listItem) {
		listView.getSelectionModel().select(listItem);
		int index = listView.getSelectionModel().getSelectedIndex();
		index = index + 1;
		model.add( index, new TodoTaskView( this, new TodoTask() ) );
	}

	@Override
	public void onTaskFocusLost(TodoTaskView task) {
		model.remove(task);
	}
	
	@Override
	public void onDeleteItem(TodoListItem item) {
		model.remove(item);
	}

	@Override
	public void onItemMove(TodoListItem to, TodoListItem from) {
		model.move( to, from);
	}
	
	
}
