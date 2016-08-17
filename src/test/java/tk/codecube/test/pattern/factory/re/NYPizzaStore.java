package tk.codecube.test.pattern.factory.re;

public class NYPizzaStore extends AbstractPizzaStore {

	private PizzaIngredientFactory pif = new NYPizzaIngredientFactory();

	@Override
	public Pizza createPizza(String type) {
		Pizza piz = null;
		switch(type)
		{
		case "cheese":
			piz = new CheesePizza(pif);
			piz.setName("NewYork Cheese Pizza");
		}
				
		return piz;
	}
}
