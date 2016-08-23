package tk.codecube.test.pattern.component;

import java.util.Iterator;

/**
 * 组合模式菜单项
 * @Description 菜单项
 * @author jianlong.song bpqqop@163.com
 * @date 2016年8月22日 下午2:40:10
 */
public class MenuItem extends MenuComponent {

	private String name;
	private String desription;
	private boolean vegetarian;
	private double price;

	public MenuItem(String name, String description, boolean vegetarian,
			double price) {
		this.name = name;
		this.desription = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	@Override
	public void print() {
		System.out.print(getName());
		if(isVegetarian())
		{
			System.out.print("(v),");
		}
		System.out.println(" Price:"+getPrice());
		System.out.println("  ---"+getDesription());
	}
	
	@Override
	public Iterator<MenuComponent> createIterator() {
		return new NullIterartor();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	@Override
	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
