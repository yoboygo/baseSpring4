package tk.codecube.test.pattern.component;

import java.util.Iterator;

import org.springframework.util.CompositeIterator;

/**
 * 组合模式顶层类
 * @Description 菜单组件
 * @author jianlong.song bpqqop@163.com
 * @date 2016年8月22日 下午2:39:48
 */
public abstract class MenuComponent {

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		throw new UnsupportedOperationException();
	}

	public void add(MenuComponent mc) {
		throw new UnsupportedOperationException();
	};

	public void remove(MenuComponent mc) {
		throw new UnsupportedOperationException();
	};

	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	};
	
	/**
	 * @Description 打印
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月22日 下午4:07:41
	 */
	public void print() {
		throw new UnsupportedOperationException();
	};
	
	/**
	 * @Description 迭代器
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月22日 下午4:07:32
	 * @return
	 */
	public Iterator<MenuComponent> createIterator()
	{
		return new CompositeIterator<MenuComponent>();
	}
	
	public boolean isVegetarian()
	{
		throw new UnsupportedOperationException();
	}
}
