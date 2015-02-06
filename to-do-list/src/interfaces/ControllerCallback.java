package interfaces; 

import ui.TodoTaskView;


public interface ControllerCallback {
	public void onAddItem( TodoListItem item );
	public void onTextFieldInterrupt( TodoTaskView task );
	public void onDeleteItem( TodoListItem item );
	public void onItemMove(TodoListItem to, TodoListItem from);
}
