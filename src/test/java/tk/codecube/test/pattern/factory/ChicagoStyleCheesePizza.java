package tk.codecube.test.pattern.factory;

public class ChicagoStyleCheesePizza extends Pizza {

	public ChicagoStyleCheesePizza() {
		setName("ChicagoStyle");
		setDough("Extra Thick Crust Dough");
		setSauce("Plum Tomato Sauce");
		addTop("Shredded Mozzarella Cheese");
	}
	
	@Override
	public void cut() {
		System.out.println("Cutting "+getName());
	}
}
