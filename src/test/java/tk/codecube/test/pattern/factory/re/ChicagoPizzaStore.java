package tk.codecube.test.pattern.factory.re;

public class ChicagoPizzaStore extends AbstractPizzaStore {

	private ChicagoPizzaIngredientFactory cpif = new ChicagoPizzaIngredientFactory();
	@Override
	public Pizza createPizza(String type) {
		Pizza piz = null;
		switch(type)
		{
		case "cheese":
			piz = new CheesePizza(cpif);
			piz.setName("Chicago Pizza");
		}
		return piz;
	}

}
