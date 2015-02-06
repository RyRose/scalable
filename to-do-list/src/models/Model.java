package models;


import application.Controller;
import interfaces.TodoListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
	
	private ObservableList<TodoListItem> itemList;
	
	public Model( Controller controller ) { setUpList(controller); }
	
	private void setUpList(Controller controller) {
		itemList = FXCollections.observableArrayList();
	}
	
	public ObservableList<TodoListItem> getObservableList() { return itemList; }
	
	public void setObservableList( ObservableList<TodoListItem> list) { itemList = list; }
	
	public int size() { return itemList.size(); }
	
	public void add( TodoListItem item) { itemList.add(item); }
	
	public void add( int index, TodoListItem item) { itemList.add( index, item ); }
	
	public void remove( TodoListItem item) { itemList.remove(item); }
	
	public void remove( int index) { itemList.remove(index); }
	
	public TodoListItem get( int index ) { return itemList.get(index); }
	
	public int getPosition( TodoListItem item) {return itemList.indexOf(item); }
	
	public void move( TodoListItem from, TodoListItem to ) {
		move( getPosition(from), getPosition(to) );
	}
	
	public void move( int from, int to ) {
		TodoListItem item = get(from);
		if ( to - from < 0 ) {
			remove(from);
			add(to, item);
		} else {
			remove(from);
			add(to - 1, item);
		}
	}
	
}
