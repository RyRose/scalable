package models;


import application.Controller;
import interfaces.TodoListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
	public static final String FILE_NAME = "CSCI-250-TodoListText.txt";
	
	private ObservableList<TodoListItem> itemList;
	
	public Model( Controller controller ) { setUpList(controller); }
	
	private void setUpList(Controller controller) {
		itemList = FXCollections.observableArrayList();
	}
	
	public ObservableList<TodoListItem> getObservableList() {
		return itemList;
	}
	
	public void setObservableList( ObservableList<TodoListItem> list) {
		itemList = list;
		System.out.println(itemList);
	}
	
	public int size() { return itemList.size(); }
	
	public void add( TodoListItem item) { itemList.add(item); }
	
	public void add( int index, TodoListItem item) { itemList.add( index, item ); }
	
	public void remove( TodoListItem item) { itemList.remove(item); }
	
	public void remove( int index) { itemList.remove(index); }
	
	public TodoListItem get( int index ) { return itemList.get(index); }
	
	public int getPosition( TodoListItem item) {return itemList.indexOf(item); }
	
	public void move( int from, int to ) {
		TodoListItem item = get(from);
		remove(from);
		add(to, item);
	}
	
	public void move( TodoListItem from, TodoListItem to ) {
		if ( getDistance( from, to ) < 0 ) {
			move( getPosition(from), getPosition(to));
		} else {
			int to_position = getPosition(to);
			int from_position = getPosition(from);
			remove(from_position);
			add(to_position - 1, from);
		}
	}
	
	public int getDistance( TodoListItem from, TodoListItem to) {
		return getPosition(to) - getPosition(from);
		
	}
	
}
