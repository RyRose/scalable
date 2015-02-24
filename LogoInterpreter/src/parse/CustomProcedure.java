package parse;

import java.util.ArrayList;

public class CustomProcedure {
	public String name;
	public String command;
	
	private ArrayList<String> parameters = new ArrayList<String>();

	public void addParameter( String parameter ) {
		parameters.add(parameter);
	}
	
	public ArrayList<String> getParameters() {
		return parameters;
	}
}
