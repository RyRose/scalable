package test;

import static org.junit.Assert.*;
import models.Speed;
import models.Turtle;

import org.junit.Test;

public class TurtleTest {

	Turtle turtle = new Turtle(0, 0, 0);
	
	@Test
	public void TestMove() {
		turtle.moveForward(40);
		assertEquals(turtle.getCoordinates()[0], 40, 0);
		assertEquals(turtle.getCoordinates()[1], 0, 0);
	}
	
	@Test
	public void TestMoveHome() {
		turtle.moveForward(1000);
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
