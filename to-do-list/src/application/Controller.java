package application;

import models.Category;
import models.Model;
import models.TodoTask;
import ui.CategoryView;
import ui.TodoTaskView;
import interfaces.ControllerCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Controller implements ControllerCallback {
	private final String[] PRESET_CATEGORIES = new String[] { "Today", "Tomorrow", "Later"};
	
	@FXML
	private ListView<HBox> listView;
	
	private ObservableList<HBox> itemList;
	private Model model;
	
	@FXML
	private void initialize() {
		model = new Model();
		itemList = FXCollections.observableArrayList();
		listView.setItems(itemList);
		addPresetCategoriesToItemList();
	}
	
	private void addPresetCategoriesToItemList() {	
		for ( int i = 0; i < PRESET_CATEGORIES.length; i++ ) {
			Category category = new Category( PRESET_CATEGORIES[i] );
			itemList.add( new CategoryView(category, this));
			model.addItem( i, category);
		}
	}

	@Override
	public void onAddTask(CategoryView category) {
		listView.getSelectionModel().select(category);
		int index = listView.getSelectionModel().getSelectedIndex();
		itemList.add( index + 1, new TodoTaskView( this, new TodoTask() ) );
	}

	@Override
	public void onTaskFocusLost(TodoTaskView task) {
		itemList.remove(task);
	}

	@Override
	public void onTaskFinished(TodoTask taskItem, HBox item) {
		model.addItem( getPosition(item), taskItem);
	}
	
	@Override
	public void onDeleteTask(TodoTaskView task, TodoTask item) {
		itemList.remove(task);
		model.removeItem(item);
	}
	
	@Override
	public int getPosition( HBox item ) {
		return itemList.indexOf( item );
	}

	
	
	
}
