package tk.codecube.test.pattern.factory.command;

public class CeilingFanCommand implements Command {

	private CeilingFan cf;
	private int preSpeed = CeilingFan.OFF;

	public CeilingFanCommand(CeilingFan cf) {
		this.cf = cf;
	}

	@Override
	public void execute() {
		preSpeed = cf.getSpeed();
		if (cf.getSpeed() == CeilingFan.OFF) {
			cf.on();
		} else {
			int sp = (cf.getSpeed() + 1) % 4;
			cf.setSpeed(sp);
		}

	}

	@Override
	public void undo() {
		cf.setSpeed(preSpeed);
	}

}
