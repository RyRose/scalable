package parse;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class InstructionEvaluator {
	
	private final String ERROR_MESSAGE = "Cannot be evaluated.";
	
	Grammar grammar;
	NumberEvaluator numberEvaluator;
	
	public InstructionEvaluator() { 
		grammar = new Grammar();
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

	private void evalTree(Tree t) {
		// System.out.println( t.getName() + " : " + t.getNumChildren());
		
		if (t.isNamed("first_cmd") && t.getNumChildren() != 1) {
			evalTree(t.getNamedChild("first_cmd"));
			evalTree(t.getNamedChild("cmd"));
		} else if ( t.isNamed("lines") && t.getNumChildren() == 2 ) {
			evalTree(t.getChild(0));
			evalTree(t.getChild(1));
		} else if (t.isNamed("line")) {
			evalTree(t.getNamedChild("first_cmd"));
		} else if ( t.isNamed("no_arg_cmds")) {
			handleNoArgumentCommands(t);
		} else if (t.isNamed("arg_cmd") ) {
			handleArgumentCommands(t);
		} else if ( t.getNumChildren() == 1 ) {
			evalTree(t.getChild(0));
		}
			
		else 
			throw new IllegalArgumentException( ERROR_MESSAGE + " " + t.toString());
	}
	
	private void handleNoArgumentCommands( Tree t ) {
		if ( InstructionDecider.hasClearscreen(t) ) {
			System.out.println("Clearscreen");
		} else if ( InstructionDecider.hasHome(t)) {
			System.out.println("Home");
		} else if ( InstructionDecider.hasPendown(t)) {
			System.out.println("Pendown");
		} else if ( InstructionDecider.hasPenup(t)) {
			System.out.println("Penup");
		} else if ( InstructionDecider.hasHideturtle(t)) {
			System.out.println("Hideturtle");
		} else if ( InstructionDecider.hasShowturtle(t)) {
			System.out.println("Showturtle");
		} else {
			throw new IllegalArgumentException("Invalid no_argument_command");
		}
	}
	
	private void handleArgumentCommands( Tree t ) {
		Tree cmds = t.getNamedChild("arg_cmds");
		if (cmds.hasNamed("fd")) {
			System.out.println("fd: " + numberEval(t.getNamedChild("num")));
		} else if (cmds.hasNamed("bk")) {
			System.out.println("bk: " + numberEval(t.getNamedChild("num")));
		} else if (cmds.hasNamed("lt")) {
			System.out.println("lt: " + numberEval(t.getNamedChild("num")));
		} else if (cmds.hasNamed("rt")) {
			System.out.println("rt: " + numberEval(t.getNamedChild("num")));			
		} else {
			throw new IllegalArgumentException("Invalid argument_command");
		}
	}
	
	private int numberEval( Tree t ) {
		return numberEvaluator.evalTree(t);
	}
	
	public static void main( String args[] ) {
		InstructionEvaluator eval = new InstructionEvaluator();
		eval.eval("bk 50 \n fd 20 \n cs lt 300 fd 10 rt 450 st \n ht");
	}
}
