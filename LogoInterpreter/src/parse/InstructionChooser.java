package parse;

import edu.hendrix.grambler.Tree;

public class InstructionChooser {
	
	private static final String FORWARD_CMD = "fd";
	private static final String BACK_CMD = "bk";
	private static final String LEFT_CMD = "lt";
	private static final String RIGHT_CMD = "rt";
	private static final String PENDOWN_CMD = "pd";
	private static final String PENUP_CMD = "pu";
	private static final String HOME_CMD = "home";
	private static final String CLEARSCREEN_CMD = "cs";
	private static final String HIDETURTLE_CMD = "ht";
	private static final String SHOWTURTLE_CMD = "st";

	public static final boolean hasForward( Tree t ) {
		return t.hasNamed(FORWARD_CMD);
	}
	public static final boolean hasBack( Tree t ) {
		return t.hasNamed(BACK_CMD);
	}

	public static final boolean hasLeft( Tree t ) {
		return t.hasNamed(LEFT_CMD);
	}
	public static final boolean hasRight( Tree t ) {
		return t.hasNamed(RIGHT_CMD);
	}

	public static final boolean isPendown( Tree t ) {
		return t.hasNamed(PENDOWN_CMD);
	}
	public static final boolean isPenup( Tree t ) {
		return t.hasNamed(PENUP_CMD);
	}
	
	public static final boolean isHome( Tree t ) {
		return t.hasNamed(HOME_CMD);
	}
	
	public static final boolean isClearscreen( Tree t ) {
		return t.hasNamed(CLEARSCREEN_CMD);
	}
	
	public static final boolean isHideturtle( Tree t ) {
		return t.hasNamed(HIDETURTLE_CMD);
	}
	
	public static final boolean isShowturtle( Tree t ) {
		return t.hasNamed(SHOWTURTLE_CMD);
	}
}
