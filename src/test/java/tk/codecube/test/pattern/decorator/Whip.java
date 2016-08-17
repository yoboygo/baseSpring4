package tk.codecube.test.pattern.decorator;

public class Whip extends Decorator {

	private Beverage be;
	
	public Whip(Beverage be) {
		this.be = be;
	}
	@Override
	public String getDescript() {
		return be.getDescript() + ", Whip";
	}

	@Override
	public double cost() {
		return 3.3 + be.cost();
	}

}
