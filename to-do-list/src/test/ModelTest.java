package test;

import static org.junit.Assert.*;
import models.Category;
import models.Model;
import models.TodoTask;

import org.junit.Test;

public class ModelTest {
	
	private final int BIG_NUMBER = 10000;
	private final String STRING = "Hello World!";
	
	Model model = new Model();

	@Test
	public void testInitialSetup() {
		assertEquals(model.size(), model.getNumberOfCategories());
		assertEquals(model.size(), 0);
	}
	
	@Test
	public void testAddingATask() {
		for( int i = 0; i < BIG_NUMBER; i++ ) {
			model.addItem(i, new TodoTask() );
		}
		
		assertEquals(model.size(), BIG_NUMBER);
		assertEquals(model.getNumberOfTasks(), BIG_NUMBER);
	}
	
	@Test
	public void testTaskTextSetting() {
		TodoTask task = new TodoTask( "test string" );
		task.setText(STRING);
		assertEquals(STRING, task.getText());
	}
	
	@Test( expected = IndexOutOfBoundsException.class)
	public void addingImproperTasksBefore() {
		model.addItem(-1, new TodoTask());
	}
	
	@Test( expected = IndexOutOfBoundsException.class)
	public void addingImproperTasksAfter() {
		model.addItem(1, new TodoTask());
	}
	
	@Test
	public void testTasksAddedAreCorrectTasks() {
		for( int i = 0; i < BIG_NUMBER; i++) {
			TodoTask task = new TodoTask(Integer.toString(i));
			assertEquals(Integer.toString(i), task.getText());
			model.addItem(i, task);
			assertEquals(model.getItem(i).getText(), Integer.toString(i));
		}
	}
	
	@Test
	public void testTaskRemoving() {
		TodoTask task = new TodoTask();
		for( int i = 0; i < BIG_NUMBER; i++) {
			task = new TodoTask( Integer.toString(i));
			model.addItem(i, task);
		}
		
		assertEquals(model.removeItem(0).getText(), "0");
		assertEquals(model.size(), 9999);
		assertEquals(model.getNumberOfTasks(), 9999);
		
		model.addItem( BIG_NUMBER - 1, new Category("abc"));
		assertEquals(model.removeItem(BIG_NUMBER - 1).getText().replace(" ", ""), "abc");
		assertEquals(model.getNumberOfCategories(), 0);
		model.removeItem(task);
		assertEquals(model.size(), BIG_NUMBER-2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testImproperTaskRemovingEmpty() {
		model.removeItem(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testImproperTaskRemovingAfter() {
		model.addItem(0, new TodoTask());
		model.removeItem(1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testImproperTaskRemovingBefore() {
		model.addItem(0, new TodoTask());
		model.removeItem(-1);
	}
	
	@Test
	public void testAddingCategories() {
		for( int i = 0; i < BIG_NUMBER; i++) {
			model.addItem(i, new Category(Integer.toString(i)));
		}
		assertEquals(model.size(), BIG_NUMBER);
		assertEquals(model.getNumberOfCategories(), BIG_NUMBER);
	}

}
