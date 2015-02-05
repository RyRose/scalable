package test;

import static org.junit.Assert.*;
import interfaces.TodoListItem;
import models.Model;

import org.junit.Test;

public class ModelTest {
	
	private final int BIG_NUMBER = 10000;
	private final String STRING = "Hello World!";
	
	Model model = new Model(null);

	@Test
	public void testInitialSetup() {
		assertEquals(model.size(), 0);
	}
	
	@Test
	public void testAddingATask() {
		for( int i = 0; i < BIG_NUMBER; i++ ) {
			model.add( new TestTask() );
		}
		
		assertEquals(model.size(), BIG_NUMBER);
	}
	
	
	@Test
	public void testTasksAddedAreCorrectTasks() {
		for( int i = 0; i < BIG_NUMBER; i++) {
			TestTask task = new TestTask(Integer.toString(i));
			assertEquals(Integer.toString(i), task.getMainText());
			model.add(task);
			assertEquals(model.get(i).getMainText(), Integer.toString(i));
		}
	}
	
	@Test
	public void testTaskRemoving() {
		TestTask task;
		final int NUM_REMOVALS = 10;
		for( int i = 0; i < NUM_REMOVALS; i++) {
			for( int j = 0; j < BIG_NUMBER/NUM_REMOVALS ; j++) {
				task = new TestTask( Integer.toString(i));
				model.add(task);
			}
			model.remove(0);
		}
		
		assertEquals(model.size(), BIG_NUMBER - NUM_REMOVALS);
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removingEmpty() {
		model.remove(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removingAfter() {
		model.add( new TestTask() );
		model.remove(1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removingBefore() {
		model.add(new TestTask());
		model.remove(-1);
	}
	
	private class TestTask implements TodoListItem {

		String text;
		
		public TestTask( String text ) {
			this.text = text;
		}
		
		public TestTask() {
			this.text = "";
		}
		
		@Override
		public String getMainText() {
			return text;
		}
		
	}

}
