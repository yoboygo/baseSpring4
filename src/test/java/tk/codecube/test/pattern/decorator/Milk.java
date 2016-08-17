package tk.codecube.test.pattern.decorator;

public class Milk extends Dectorator {

	private Beverage be;
 	public Milk(Beverage be) {
 		this.be = be;
	}
	
	@Override
	public double cost() {
		return 2 + be.cost();
	}

	@Override
	public String getDescript() {
		return be.getDescript()+", Milk";
	}

}
