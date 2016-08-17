package tk.codecube.test.pattern.factory;

public class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String style) {

		Pizza piz = null;
		switch (style) {
		case "cheese":
			piz = new NYStyleCheesePizza();
		case "veggie":
			piz = new NYStyleVeggiePizza();
		}

		return piz;
	}

}
