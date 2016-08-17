package tk.codecube.test.pattern.factory;

import java.util.ArrayList;

public abstract class Pizza {

	private String name;
	private String dough;
	private String sauce;
	private ArrayList<String> toppings = new ArrayList<String>();
	
	public void prepare(){
		System.out.println("Prepare "+this.getName()+" Pizza!");
	}
	
	public void cut()
	{
		System.out.println("Cut "+this.getName()+" Pizza!");
	}
	
	public void bake(){
		System.err.println("Bake "+this.getName()+" Pizza!");
	}
	
	public void box(){
		System.out.println("Box "+this.getName()+" Pizza!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDough() {
		return dough;
	}

	public void setDough(String dough) {
		this.dough = dough;
	}

	public String getSauce() {
		return sauce;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public ArrayList<String> getToppings() {
		return toppings;
	}
	
	public void addTop(String top)
	{
		this.toppings.add(top);
	}
	
}
