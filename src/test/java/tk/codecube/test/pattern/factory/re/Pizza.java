package tk.codecube.test.pattern.factory.re;

public abstract class Pizza {

	private String name;
	private Dough dough;
	private Sauce sauce;
	private Cheese cheese;

	public abstract void prepare();

	public void bake() {
		System.out.println("Bake " + getName());
	}

	public void cut() {
		System.out.println("Cut " + getName());
	}

	public void box() {
		System.out.println("Box " + getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public Dough getDough() {
		return dough;
	}

	public void setDough(Dough dough) {
		this.dough = dough;
	}

	public Sauce getSauce() {
		return sauce;
	}

	public void setSauce(Sauce sauce) {
		this.sauce = sauce;
	}

	public Cheese getCheese() {
		return cheese;
	}

	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
	}


}
