package tk.codecube.test.pattern.factory.re;


public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinareSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

}
