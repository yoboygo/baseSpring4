package tk.codecube.test.pattern.factory.re;

public interface PizzaIngredientFactory {
	
	public Dough createDough();

	public Sauce createSauce();

	public Cheese createCheese();

}
