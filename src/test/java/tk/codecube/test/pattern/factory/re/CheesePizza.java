package tk.codecube.test.pattern.factory.re;

public class CheesePizza extends Pizza {

	private PizzaIngredientFactory pif;

	public CheesePizza(PizzaIngredientFactory pif) {
		this.pif = pif;
	}

	@Override
	public void prepare() {

		setDough(pif.createDough());
		setSauce(pif.createSauce());
		setCheese(pif.createCheese());
	}

}
