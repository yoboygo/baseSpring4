package tk.codecube.test.pattern.factory.command;

public class GarageDoorOpenCommand implements Command {

	private Door door;

	public GarageDoorOpenCommand(Door d) {
		this.door = d;
	}

	@Override
	public void execute() {
		door.on();
	}

	@Override
	public void undo() {
		door.off();
	}

}
