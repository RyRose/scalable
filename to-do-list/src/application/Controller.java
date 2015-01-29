package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Controller {
	
	@FXML
	private ListView<HBox> listView;
		
	private Model model;
	
	@FXML
	private void initialize() {
		model = new Model();
		listView.setItems(model.getObservableList());
	}
}
