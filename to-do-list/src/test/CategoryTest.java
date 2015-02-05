package test;

import static org.junit.Assert.*;
import models.Category;

import org.junit.Test;

public class CategoryTest {

	private final String STRING = "Hello World!";
	
	Category category = new Category();
	
	@Test
	public void initialTextSet() {
		assertEquals( "", category.getMainText());
		category = new Category( STRING );
		assertEquals(category.getMainText(), STRING);
	}
	
	@Test
	public void changeText() {
		category.setText(STRING);
		assertEquals( category.getMainText(), STRING);
	}

}
