package tk.codecube.test.pattern.factory.re;

public abstract class AbstractPizzaStore {
	
	public Pizza orderPizza(String type)
	{
		return createPizza(type);
	}
	
	public abstract Pizza createPizza(String type);

}
