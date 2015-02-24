package parse;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

abstract public class BaseEvaluator {
	private final String ERROR_MESSAGE = "Cannot be evaluated.";
	
	private Grammar grammar;
	private NumberEvaluator numberEvaluator;
	
	private HashMap<String, CustomProcedure> customProcedures;
	
	protected abstract void handleClearscreenCommand();
	protected abstract void handleHomeCommand();
	protected abstract void handlePendownCommand();
	protected abstract void handlePenupCommand();
	protected abstract void handleHideturtleCommand();
	protected abstract void handleShowturtleCommand();
	protected abstract void handleMoveForwardCommand( int absolute_units );
	protected abstract void handleMoveBackwardCommand( int absolute_units );
	protected abstract void handleRotateLeftCommand( int degrees );
	protected abstract void handleRotateRightCommand( int degrees );

	
	public BaseEvaluator() { 
		grammar = new Grammar();
		customProcedures = new HashMap<String, CustomProcedure>();
		numberEvaluator = new NumberEvaluator();
	}
	
	public void eval(String input) {
		try {
			Tree tree = grammar.parse(input);
			evalTree(tree);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}

	private void evalTree(Tree t) throws ParseException {
		if (t.isNamed("first_cmd") && t.getNumChildren() != 1) {
			evalTree(t.getNamedChild("first_cmd"));
			evalTree(t.getNamedChild("cmd"));
		} else if ( t.hasNamed("lines") ) {
			evalTree(t.getChild(0));
			evalTree(t.getChild(1));
		} else if (t.isNamed("line"))
			evalTree(t.getNamedChild("first_cmd"));
		else if ( t.isNamed("no_arg_cmds"))
			handleNoArgumentCommands(t);
		else if (t.isNamed("arg_cmd") )
			handleArgumentCommands(t);
		else if (t.isNamed("repeat_cmd"))
			handleRepeatCommand(t);
		else if (t.isNamed("if_cmd") )
			handleIfCommand(t);
		else if (t.isNamed("ifelse_cmd") )
			handleIfElseCommand(t);
		else if (t.isNamed("cond_statement"))
			handleCondStatementCommand(t);
		else if (t.isNamed("cond_paren"))
			handleCondParenCommand(t);
		else if (t.isNamed("procedure_cmd"))
			handleProcedureCommand(t);
		else if (t.isNamed("custom_procedure_cmd"))
			handleCustomProcedureCommand(t);
		else if ( t.getNumChildren() == 1 )
			evalTree(t.getChild(0));
		else 
			throw new IllegalArgumentException( ERROR_MESSAGE + " " + t.toString());
	}
	
	private void handleNoArgumentCommands( Tree t ) {
		if (InstructionChooser.isClearscreen(t))
			handleClearscreenCommand();
		else if (InstructionChooser.isHome(t)) 
			handleHomeCommand();
		else if (InstructionChooser.isPendown(t))
			handlePendownCommand();
		else if (InstructionChooser.isPenup(t))
			handlePenupCommand();
		else if (InstructionChooser.isHideturtle(t))
			handleHideturtleCommand();
		else if (InstructionChooser.isShowturtle(t))
			handleShowturtleCommand();
		else
			throw new IllegalArgumentException("Invalid no_argument_command");
	}
	
	private void handleArgumentCommands( Tree t ) {
		Tree cmds = t.getNamedChild("arg_cmds");
		int num = numberEval(t.getNamedChild("num"));
		
		if (InstructionChooser.hasForward(cmds))
			handleMoveForwardCommand(num);
		else if (InstructionChooser.hasBack(cmds))
			handleMoveBackwardCommand(num);
		else if (InstructionChooser.hasLeft(cmds))
			handleRotateLeftCommand(num);
		else if (InstructionChooser.hasRight(cmds))
			handleRotateRightCommand(num);
		else
			throw new IllegalArgumentException("Invalid argument_command");
	}
	
	private void handleRepeatCommand( Tree t ) throws ParseException {
		int repeat_count = numberEval(t.getNamedChild("num"));
		for( int i = 0; i < repeat_count; i++)
			evalTree( t.getNamedChild("bracket").getNamedChild("first_cmd"));
	}
	
	private void handleIfCommand( Tree t ) throws ParseException {
		if ( handleCondStatementCommand(t.getNamedChild("cond_statement")) )
			evalTree(t.getNamedChild("bracket").getNamedChild("first_cmd") );
	}
	
	private void handleIfElseCommand( Tree t ) throws ParseException {
		if ( handleCondStatementCommand(t.getNamedChild("cond_statement")))
			evalTree(t.getNamedChild("bracket", 0).getNamedChild("first_cmd") );
		else
			evalTree(t.getNamedChild("bracket", 1).getNamedChild("first_cmd") );
	}
	
	private boolean handleCondStatementCommand( Tree t ) {
		if ( t.hasNamed("cond_statement"))
			return handleLinkedCondStatement(t);
		else
			return handleCondParenCommand(t.getLastChild());
	}
	
	private boolean handleLinkedCondStatement( Tree t ) {
		if ( t.getNamedChild("linker").toString().equals("and") )
			return handleCondStatementCommand(t.getNamedChild("cond_statement"))
					&& handleCondParenCommand(t.getNamedChild("cond_paren"));
		else
			return handleCondStatementCommand(t.getNamedChild("cond_statement"))
					|| handleCondParenCommand(t.getNamedChild("cond_paren"));
	}
	
	private boolean handleCondParenCommand( Tree t ) {
		if ( t.hasNamed("not") )
			return !handleCondParenCommand(t.getNamedChild("cond_paren"));
		else
			return handleConditional(t);
	}
	
	private boolean handleConditional( Tree t ) {
		Tree conditional = t.getNamedChild("cond");
		int left = numberEval(t.getNamedChild("num", 0));
		int right = numberEval(t.getNamedChild("num", 1));
		
		if ( conditional.toString().contains("<="))
			return left <= right;
		else if ( conditional.toString().contains(">="))
			return left >= right;
		else if ( conditional.toString().contains("<"))
			return left < right;
		else if ( conditional.toString().contains("="))
			return left == right;
		else if ( conditional.toString().contains(">"))
			return left > right;
		else
			throw new IllegalArgumentException( conditional.toString() + ":" + "Invalid no_conditional");
	}
	
	private void handleProcedureCommand( Tree t ) {
		CustomProcedure  customProcedure = new CustomProcedure();
		customProcedure.name = t.getNamedChild("name").toString();
		customProcedure.command = t.getNamedChild("bracket").getNamedChild("first_cmd").toString();
		
		while(t.hasNamed("parameters")) {
			t = t.getNamedChild("parameters");
			customProcedure.addParameter( t.getNamedChild("param").toString() );
		}
		customProcedures.put(customProcedure.name, customProcedure);
	}
	
	private void handleCustomProcedureCommand( Tree t ) throws ParseException {
		CustomProcedure procedure = customProcedures.get(t.getChild(0).toString());
		ArrayList<String> values = new ArrayList<String>();
		System.out.println(t.getChild(3).getName());
		while( t.hasNamed("custom_params")) {
			t = t.getNamedChild("custom_params");
			values.add(String.valueOf(numberEval(t.getNamedChild("num"))));
		}
		numberEvaluator.addParameters(procedure.getParameters(), values);
		evalTree(grammar.parse(procedure.command));
	}
	
	private int numberEval( Tree t ) {
		return numberEvaluator.evalTree(t);
	}
}
