package test;

import static org.junit.Assert.*;
import models.Turtle;

import org.junit.Test;

public class TurtleTest {

	Turtle turtle = new Turtle(0, 0, 0);
	
	@Test
	public void TestMove() {
		turtle.move(40);
		assertEquals(turtle.getCoordinates()[0], 40, 0);
		assertEquals(turtle.getCoordinates()[1], 0, 0);
	}
	
	@Test
	public void TestMoveHome() {
		turtle.move(1000);
		turtle.moveHome();
		assertEquals(turtle.getCoordinates()[0], 0, 0);
		assertEquals(turtle.getCoordinates()[1], 0, 0);
	}
	
	@Test
	public void testRotate() {
		turtle.rotateLeft(90);
		assertEquals(turtle.getDirection(), -90);
		turtle.rotateRight(90);
		assertEquals(turtle.getDirection(), 0);
	}
}
