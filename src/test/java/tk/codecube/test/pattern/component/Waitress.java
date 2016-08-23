package tk.codecube.test.pattern.component;

import java.util.Iterator;

/**
 * 组合模式招待
 * 
 * @Description 招待类
 * @author jianlong.song bpqqop@163.com
 * @date 2016年8月22日 下午2:40:42
 */
public class Waitress {

	private MenuComponent allMenus;

	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}

	/**
	 * @Description 打印菜单
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月22日 下午2:39:35
	 */
	public void printMenu() {
		allMenus.print();
	}

	public void printVegetarianMenu() {
		Iterator<MenuComponent> iterator = allMenus.createIterator();
		while (iterator.hasNext()) {
			MenuComponent mc = iterator.next();
			try {
				if (mc.isVegetarian()) {
					mc.print();
				}
			} catch (UnsupportedOperationException e) {
//				System.out.println(mc.getName()+" is a menu!");
			}
		}
	}

	public MenuComponent getAllMenus() {
		return allMenus;
	}

	public void setAllMenus(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}

}
