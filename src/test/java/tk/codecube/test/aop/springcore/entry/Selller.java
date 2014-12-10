/**
 * Selller.java
 * 
 * Aimy
 * 下午12:44:16
 */
package tk.codecube.test.aop.springcore.entry;

/**
 * @author Aimy
 * 2014年12月10日 下午12:44:16
 */
public class Selller implements ISeller{

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.entry.ISeller#sell(java.lang.String)
	 */
	@Override
	public void sell(String goods) {
		System.out.println("Selller.sell()-->"+goods);
	}

}
