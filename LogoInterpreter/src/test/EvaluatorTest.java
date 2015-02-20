package test;

import org.junit.Test;

import parse.BaseEvaluator;

public class EvaluatorTest {
	
	TestEvaluator evaluator = new TestEvaluator();
	
	@Test
	public void testClearscreenCommand() {
		evaluator.eval("cs");
		evaluator.eval("cs\n");
		evaluator.eval("clearscreen");
		evaluator.eval("clearscreen\n");
	}
	
	@Test
	public void testHomeCommand() {
		evaluator.eval("home");
		evaluator.eval("home\n");
	}
	
	@Test
	public void testPendownCommand() {
		evaluator.eval("pd");
		evaluator.eval("pendown");
		evaluator.eval("pd\n");
		evaluator.eval("pendown\n");
	}
	
	@Test
	public void testPenupCommand() {
		evaluator.eval("pu");
		evaluator.eval("penup");
		evaluator.eval("pu\n");
		evaluator.eval("penup\n");
	}
	
	@Test
	public void testHideturtleCommand() {
		evaluator.eval("ht");
		evaluator.eval("hideturtle");
		evaluator.eval("ht\n");
		evaluator.eval("hideturtle\n");
	}
	
	@Test
	public void testShowTurtleCommand() {
		evaluator.eval("st");
		evaluator.eval("showturtle");
		evaluator.eval("st\n");
		evaluator.eval("showturtle\n");
	}
	
	@Test
	public void testMoveForwardCommand() {
		evaluator.eval("fd 50");
		evaluator.eval("forward 50");
		evaluator.eval("fd 50\n");
		evaluator.eval("forward 50\n");
	}
	
	@Test
	public void testMoveBackwardCommand() {
		evaluator.eval("bk 50");
		evaluator.eval("back 50");
		evaluator.eval("bk 50\n");
		evaluator.eval("back 50\n");
	}
	
	@Test
	public void testRotateLeftCommand() {
		evaluator.eval("lt 50");
		evaluator.eval("left 50");
		evaluator.eval("lt 50\n");
		evaluator.eval("left 50\n");
	}
	
	@Test
	public void testRotateRightCommand() {
		evaluator.eval("rt 50");
		evaluator.eval("right 50");
		evaluator.eval("rt 50\n");
		evaluator.eval("right 50\n");
	}
	
	@Test
	public void testLines() {
		evaluator.eval("ht\nst\nfd 50");
	}
	
	@Test
	public void testRepeatCommand() {
		evaluator.eval("repeat 3 [bk 50 fd 50]");
	}
	
	@Test
	public void testIfCommand() {
		evaluator.eval("if 3 < 5 [fd 50]");
		evaluator.eval("if (5 = 4) or (3 < 7) [fd 20 rt 30]");
		evaluator.eval("if (5 = 4) and (3 < 7) [fd 20 rt 30]");
	}
	
	@Test
	public void testIfElseCommand() {
		evaluator.eval("ifelse 3 < 5 [fd 50] [rt 25]");
	}
	
	@Test
	public void testProcedureCommand() {
		evaluator.eval("to triangle :size [repeat 3 [fd :size rt 120]]");
		evaluator.eval("to fractal :size [repeat 3 [fracside :size / 3 rt 120]]");
		evaluator.eval("to fracside :size [ifelse :size <= 1 [fd 1] [fd :size lt 60 fracside :size / 3 rt 120 fracside :size / 3 lt 60 fd :size]]");
	}
	
	@Test
	public void testCustomProcedureCommand() {
		evaluator.eval("to triangle :size [repeat 3 [fd :size rt 120]]");
		evaluator.eval("triangle 3");
	}
	
	@Test
	public void testConditionalStatements() {
		evaluator.eval("if 3 <= 5 [fd 50]");
		evaluator.eval("if 3 < 5 [fd 50]");
		evaluator.eval("if 3 = 5 [fd 50]");
		evaluator.eval("if 3 > 5 [fd 50]");
		evaluator.eval("if 3 >= 5 [fd 50]");
		evaluator.eval("if not 3 < 5 [fd 50]");
	}
	
	@Test
	public void testArithmetic() {
		evaluator.eval("fd 50 / 3" );
		evaluator.eval("fd 50 - 3" );
		evaluator.eval("fd 50 + 3" );
		evaluator.eval("fd 50 * 3" );
	}
	
	private class TestEvaluator extends BaseEvaluator {
		@Override protected void handleClearscreenCommand() {}
		@Override protected void handleHomeCommand() {}
		@Override protected void handlePendownCommand() {}
		@Override protected void handlePenupCommand() {}
		@Override protected void handleHideturtleCommand() {}
		@Override protected void handleShowturtleCommand() {}
		@Override protected void handleMoveForwardCommand(int absolute_units) {}
		@Override protected void handleMoveBackwardCommand(int absolute_units) {}
		@Override protected void handleRotateLeftCommand(int degrees) {}
		@Override protected void handleRotateRightCommand(int degrees) {}
	}
}