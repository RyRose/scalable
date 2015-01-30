package interfaces; 

import models.TodoTask;
import javafx.scene.layout.HBox;
import ui.CategoryView;
import ui.TodoTaskView;


public interface ControllerCallback {
	public void onAddTask( CategoryView category );
	public void onTaskFocusLost( TodoTaskView task );
	public void onTaskFinished( TodoTask listItem, HBox item );
	public int getPosition( HBox item );
	public void onDeleteTask( TodoTaskView task, TodoTask item );
}
