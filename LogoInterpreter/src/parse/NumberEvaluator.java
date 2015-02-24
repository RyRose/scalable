package parse;

import java.util.HashMap;
import java.util.List;

import edu.hendrix.grambler.Tree;

public class NumberEvaluator {
	
	public static final String LINES = "lines";
	public static final String LINE = "line";
	public static final String ASSIGN_EXPR = "assign_expr";
	public static final String ADDSUB_EXPR = "expr";
	public static final String MULTDIV_EXPR = "expr2";
	public static final String PARENTHESIS = "paren";
	public static final String NUMBER = "number";
	public static final String VARIABLE = "var";
	
	public static final String ADDSUB = "as";
	public static final String MULTDIV = "md";
	
	public static final String ASSIGN = "assign";
	public static final String ADD = "add";
	public static final String SUBTRACT = "sub";
	public static final String MULTIPLY = "multiply";
	public static final String DIVIDE = "divide";
	
	
	private final String ERROR_MESSAGE = "Cannot be evaluated.";
	
	private HashMap<String, Integer> numKeys;
	
	public NumberEvaluator() {
		numKeys = new HashMap<String, Integer>();
	}
	
	public void addParameter(String param, int value) {
		numKeys.put(param, value);
		
	}
	
	public void addParameters(List<String> params, List<String> values) {
		for(int i = 0; i < params.size(); i++) {
			numKeys.put(params.get(i), Integer.valueOf(values.get(i)));
		}
	}

		
	public int evalTree( Tree t ) {
		return Integer.parseInt(eval(t));
	}

	private String eval(Tree t) {
		if ( isNumber(t) ) 
			return handleNumber(t);
		
		else if ( isParam(t) )
			return handleParam(t);
		
		else if ( isAlone(t) ) 
			return handleAlone(t);
		
		else if ( isAdditionSubtraction(t) ) 
			return handleAdditionSubtraction(t);
		
		else if ( isMultiplicationDivision(t) ) 
			return handleMultiplicationDivision(t);
		
		else if ( isParenthesis(t) ) 
			return handleParenthesis(t);
		
		else 
			throw new IllegalArgumentException( ERROR_MESSAGE + " " + t.toString());
	}
	
	private boolean isNumber( Tree t ) {
		return t.isNamed("number");
	}
	
	private String handleNumber( Tree t ) {
		return t.toString();
	}
	
	private boolean isParam( Tree t ) {
		return t.isNamed("param");
	}
	
	private String handleParam( Tree t ) {
		return String.valueOf(numKeys.get(t.toString()));
	}
	
	private boolean isAlone( Tree t ) {
		return t.getNumChildren() == 1;
	}
	
	private String handleAlone( Tree t ) {
		return eval(t.getChild(0));
	}
	
	private boolean isAdditionSubtraction( Tree t ) {
		return t.isNamed(ADDSUB_EXPR);
	}
	
	private String handleAdditionSubtraction( Tree t ) {
		Tree ret = t.getNamedChild(ADDSUB_EXPR);
		Tree ret2 = t.getNamedChild(MULTDIV_EXPR);
		
		if ( t.getNamedChild(ADDSUB).hasNamed(ADD) )
			return String.valueOf( Integer.parseInt(eval(ret)) + Integer.parseInt(eval(ret2)));
		else 
			return String.valueOf( Integer.parseInt(eval(ret)) - Integer.parseInt(eval(ret2)));
	}
	
	private boolean isMultiplicationDivision( Tree t ) {
		return t.isNamed(MULTDIV_EXPR);
	}
	
	private String handleMultiplicationDivision( Tree t ) {
		Tree ret = t.getNamedChild(MULTDIV_EXPR);
		Tree ret2 = t.getNamedChild(PARENTHESIS);
		if ( t.getNamedChild(MULTDIV).hasNamed(MULTIPLY) ) 
			return String.valueOf( Integer.parseInt(eval(ret)) * Integer.parseInt(eval(ret2)));
		else
			return String.valueOf( Integer.parseInt(eval(ret)) / Integer.parseInt(eval(ret2)));
	}
	
	private boolean isParenthesis( Tree t ) {
		return t.isNamed(PARENTHESIS );
	}
	
	private String handleParenthesis( Tree t ) {
		return eval( t.getNamedChild(ADDSUB_EXPR) );
	}
}
