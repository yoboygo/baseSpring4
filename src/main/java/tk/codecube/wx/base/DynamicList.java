/**
 * DynamicList.java
 * 
 * Aimy
 * 下午3:09:50
 */
package tk.codecube.wx.base;

import java.util.ArrayList;

/**
 * 动态List
 * 
 * @author Aimy 2014年11月25日 下午3:09:50
 */
public class DynamicList<E> extends ArrayList<E> {

	protected Class<E> clazz;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536223626100037377L;

	public DynamicList(Class<E> clazz) {
		this.clazz = clazz;
	}

	public E get(int index) {

		while (index >= super.size()) {
			try {
				super.add(clazz.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return super.get(index);
	}
}
