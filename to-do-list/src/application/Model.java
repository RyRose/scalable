package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

public class Model {

	private final String[] PRESET_CATEGORIES = new String[] { "Today", "Tomorrow", "Later"};

	private ObservableList<HBox> itemList;
	
	public Model() { 
		itemList = FXCollections.observableArrayList();
		addPresetCategoriesToListItemList();
		}
	
	private void addPresetCategoriesToListItemList() {	
		Category main_category;
		for( String s : PRESET_CATEGORIES) {
			main_category = new Category(s, itemList);
			itemList.add(main_category);
		}
	}
	
	public ObservableList<HBox> getObservableList() {
		return itemList;
	}
	
}
