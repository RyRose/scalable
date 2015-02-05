package ui;


import models.TodoTask;
import interfaces.ControllerCallback;
import interfaces.TodoListItem;


public class TodoTaskView extends BaseTodoListItemView implements TodoListItem {
	
	
	private TodoTask task;
	
	private ControllerCallback callback;
	
	private LeftTodoTaskView leftView;
	
	
	public TodoTaskView( ControllerCallback callback, TodoTask task ) {
		super( callback );
		this.task = task;
		this.callback = callback;
		this.leftView = new LeftTodoTaskView(this, task);
		setUpLeftView();
		
	}
	
	@Override
	public String getMainText() {
		return task.getMainText();
	}
	
	void onDeleteItem() {
		callback.onDeleteItem(this);
	}
	
	void onTaskFocusLost() {
		callback.onTaskFocusLost(this);
	}
	
	private void setUpLeftView() {
		setLeft(leftView);
	}
	
	@Override
	public String toString() {
		return task.toString();
	}
}
