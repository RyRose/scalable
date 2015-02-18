package application;

import models.Model;
import javafx.fxml.FXML;

public class Controller {
	
	Model model;

	@FXML
	void initialize() {
		model = new Model();
		System.out.println("Hello World!");
	}
}
