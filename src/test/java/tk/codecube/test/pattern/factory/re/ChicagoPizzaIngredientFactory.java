package tk.codecube.test.pattern.factory.re;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		return new ChicagoDough();
	}

	@Override
	public Sauce createSauce() {
		return new ChicagoSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ChicagoCheese();
	}

}
