package tk.codecube.test.pattern.component;

import java.util.Iterator;
import java.util.Stack;

public class ComponentUnsupportIterator implements Iterator<MenuComponent> {

	private Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();

	public ComponentUnsupportIterator(Iterator<MenuComponent> iterator) {
		stack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		} else {
			Iterator<MenuComponent> iterator = stack.peek();
			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();
			}
			return true;
		}
	}

	@Override
	public MenuComponent next() {
		if (hasNext()) {
			Iterator<MenuComponent> iterator = stack.peek();
			MenuComponent item = iterator.next();
			if (item instanceof Menu) {
				stack.push(item.createIterator());
			}
			return item;
		} else {
			return null;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
