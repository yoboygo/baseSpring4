package tk.codecube.test.pattern.component;

import java.util.Iterator;

public class NullIterartor implements Iterator<MenuComponent> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public MenuComponent next() {
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
