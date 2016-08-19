package tk.codecube.test.pattern.factory.command;

public class LightOpenCommand implements Command {

	private Light light;

	public LightOpenCommand(Light l) {
		this.light = l;
	}

	@Override
	public void execute() {
		light.on();

	}

	@Override
	public void undo() {
		light.off();
	}

}
