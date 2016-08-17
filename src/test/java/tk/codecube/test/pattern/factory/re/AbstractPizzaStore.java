package tk.codecube.test.pattern.factory.re;

public abstract class AbstractPizzaStore {
	
	public Pizza orderPizza(String type)
	{
		Pizza piz = createPizza(type);
		piz.prepare();
		piz.bake();
		piz.cut();
		piz.box();
		
		return piz;
	}
	
	public abstract Pizza createPizza(String type);

}
