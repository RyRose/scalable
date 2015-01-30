package models;

import interfaces.TodoListItem;

public class TodoTask implements TodoListItem {

	private String taskText;
	
	public TodoTask( String s ) {
		taskText = s;
	}
	
	public TodoTask() {
		taskText = "";
	}
	
	@Override
	public String getText() {
		return taskText;
	}
	
	public void setText( String s ) {
		taskText = s;
	}
	
}
