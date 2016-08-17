package tk.codecube.test.pattern.decorator;

public class HouseBlend extends Beverage {

	public HouseBlend() {
		descript = "HouseBlend";
	}
	
	@Override
	public double cost() {
		return 4.0;
	}

}
