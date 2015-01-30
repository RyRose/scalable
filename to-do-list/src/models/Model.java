package models;


import interfaces.TodoListItem;

import java.util.ArrayList;


public class Model {

	private int num_categories;
	private int num_tasks;
	
	private ArrayList<TodoListItem> itemList;
	
	public Model() { 
		itemList = new ArrayList<TodoListItem>();
		num_categories = 0;
		num_tasks = 0;
	}
	
	public int getNumberOfCategories() {
		return num_categories;
	}
	
	public int getNumberOfTasks() {
		return num_tasks;
	}
	
	public TodoListItem getItem( int index ) {
		return itemList.get(index);
	}
	
	public int size() {
		return itemList.size();
	}
	
	public void addItem( int index, TodoListItem item) {
		itemList.add( index, item);
		incrementItems(item);
	}
	
	private void incrementItems( TodoListItem item ) {
		if ( item instanceof Category)
			num_categories++;
		else
			num_tasks++;
	}
	
	public TodoListItem removeItem( int index ) {
		TodoListItem item;
		item = itemList.remove(index);
		decrementItems(item);
		return item;
	}
	
	public void removeItem( TodoListItem item) {
		itemList.remove(item);
		decrementItems(item);
	}
	
	private void decrementItems( TodoListItem item ) {
		if ( item instanceof Category)
			num_categories--;
		else
			num_tasks--;
	}
	
}
