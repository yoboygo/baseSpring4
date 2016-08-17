package tk.codecube.test.pattern.factory;

public abstract class PizzaStore {

	public abstract Pizza createPizza(String style);
	
	public Pizza orderPizza(String style){
		Pizza piz = createPizza(style);
		
		piz.prepare();
		piz.bake();
		piz.cut();
		piz.box();
		
		return piz;
	}
	
}
