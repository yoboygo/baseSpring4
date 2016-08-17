package tk.codecube.test.pattern.decorator;

public class Mocha extends Dectorator {

	private Beverage be;
	public Mocha(Beverage be) {
		this.be = be;
	}
	
	@Override
	public double cost() {
		return 3.0 + be.cost();
	}

	@Override
	public String getDescript() {
		return be.getDescript() + ", Mocha";
	}

}
