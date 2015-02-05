package models;


public class TodoTask {
	public static final String TITLE = "TodoTask";
	
	private String taskText;
	private boolean isEnabled = true;
	
	public TodoTask( String s, boolean enabled ) {
		taskText = s;
		isEnabled = enabled;
	}
	
	public TodoTask( String s ) { taskText = s;	}
	
	public TodoTask() {	taskText = ""; }
	
	public String getMainText() { return taskText; }
	
	public void setText( String s ) { taskText = s; }
	
	public boolean isEnabled() { return isEnabled; }
	
	public void enable() { isEnabled = true; }
	
	public void disable() { isEnabled = false; }
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(TITLE).append("\n");
		builder.append(taskText).append("\n");
		builder.append(isEnabled).append("\n");
		return builder.toString();
	}
	
}
