package tk.codecube.test.pattern.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 组合模式菜单
 * 
 * @Description 菜单
 * @author jianlong.song bpqqop@163.com
 * @date 2016年8月22日 下午2:40:24
 */
public class Menu extends MenuComponent {

	private List<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
	private String name;
	private String description;

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void add(MenuComponent mc) {
		menuComponents.add(mc);
	}

	@Override
	public void remove(MenuComponent mc) {
		menuComponents.remove(mc);
	}

	public MenuComponent getChild(int i) {
		return menuComponents.get(i);
	}

	@Override
	public void print() {
		System.out.println("	" + getName() + "," + getDescription());
		System.out.println("----------------------------------------");

		Iterator<MenuComponent> imc = menuComponents.iterator();
		while (imc.hasNext()) {
			imc.next().print();
		}
	}

	@Override
	public Iterator<MenuComponent> createIterator() {
		return new ComponentUnsupportIterator(menuComponents.iterator());
	}
	
	public List<MenuComponent> getMenuComponents() {
		return menuComponents;
	}

	public void setMenuComponents(List<MenuComponent> menuComponents) {
		this.menuComponents = menuComponents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
