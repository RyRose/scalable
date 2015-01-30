package models;

import interfaces.TodoListItem;

public class Category implements TodoListItem {
	
	private String name;

	public Category( String s ) {
		name = s;
	}
	
	@Override
	public String getText() {
		return name + "    ";
	}

}
