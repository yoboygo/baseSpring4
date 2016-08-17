package tk.codecube.test.pattern.decorator;

public abstract class Beverage {
	
	public String descript = "Unknow descript!";
	
	public String getDescript()
	{
		return this.descript;
	}
	
	public abstract double cost();

}
