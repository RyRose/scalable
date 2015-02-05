package models;

public class Category {
	public static final String TITLE = "Category";
	
	private String name;
	
	public Category() {
		name = "";
	}

	public Category( String s ) {
		name = s;
	}
	
	public String getMainText() {
		return name;
	}
	
	public void setText( String s ) {
		this.name = s;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(TITLE).append("\n");
		builder.append(getMainText()).append("\n");
		return builder.toString();
	}

}
