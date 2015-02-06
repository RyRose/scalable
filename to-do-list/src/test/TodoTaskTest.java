package test;

import static org.junit.Assert.*;
import models.TodoTask;

import org.junit.Test;

public class TodoTaskTest {

	private final String STRING = "Hello World!";
	
	TodoTask task = new TodoTask();
	
	@Test
	public void initialTextSet() {
		assertEquals(task.getMainText(), "");
	}
	
	@Test
	public void changeText() {
		task.setText(STRING);
		assertEquals(STRING, task.getMainText());
	}
}
