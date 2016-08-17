package tk.codecube.test.pattern.decorator;

public class Espresso extends Beverage {

	
	@Override
	public String getDescript() {
		return "Expresso";
	}

	@Override
	public double cost() {
		return 2.5;
	}

}
