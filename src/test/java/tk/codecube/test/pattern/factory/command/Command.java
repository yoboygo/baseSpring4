package tk.codecube.test.pattern.factory.command;

public interface Command {
	
	public void execute();

	public void undo();
}
