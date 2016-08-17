package tk.codecube.test.pattern.factory;

public class ChiocagoPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String style) {
		Pizza piz = null;
		switch(style)
		{
		case "cheese":
			piz = new ChicagoStyleCheesePizza();
		}
		return piz;
	}

}
