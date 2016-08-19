package tk.codecube.test.pattern.factory.command;

import java.util.ArrayList;
import java.util.List;

public class SimpleRemoteController {

	private List<Command> doCommand;
	private Command currentDoCommand;

	public SimpleRemoteController() {
		doCommand = new ArrayList<Command>();
	}

	public void addDoSomebody(int solt, Command doOpen) {
		doCommand.add(solt, doOpen);
	}

	public void doButtonPressed(int solt) {
		currentDoCommand = doCommand.get(solt);
		currentDoCommand.execute();
	}
	
	public void undoButtonPressed(int solt) {
		doCommand.get(solt).undo();
	}

	public void resetButternPressed() {
		currentDoCommand.undo();
	}
}
