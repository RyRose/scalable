package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import models.Category;
import models.TodoTask;
import ui.CategoryView;
import ui.TodoTaskView;
import interfaces.TodoListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileHandler {

	public static final String FILE_NAME = "CSCI-250-TodoListText.txt";
	
	public static ObservableList<TodoListItem> read( Controller controller ) {
		List<String> lines;
		ObservableList<TodoListItem> list = FXCollections.observableArrayList();
		
		try {
			lines = Files.readAllLines(Paths.get(FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
			return list;
		}
		
		for( int i = 0; i < lines.size(); i++) {
			if ( lines.get(i).equals(TodoTask.TITLE) ) {
				TodoTask task = new TodoTask(lines.get(i+1), Boolean.valueOf(lines.get(i+2)) );
				TodoListItem item = new TodoTaskView(controller, task);
				list.add(item);
			} else if (lines.get(i).equals(Category.TITLE)){
				Category category = new Category(lines.get(i+1));
				TodoListItem item= new CategoryView(category, controller);
				list.add(item);
			}
		}
		
		return list;
	}
	
	public static void write( ObservableList<TodoListItem> list) {
		BufferedWriter writer = null;;
		
		try {
			FileWriter fstream = new FileWriter(FILE_NAME, false);
			writer = new BufferedWriter(fstream);
			for ( TodoListItem item : list ) {
				writer.write(item.toString());
			}
		} catch ( IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch ( IOException e) {}
		}
	}
}
