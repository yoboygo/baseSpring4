/**
 * Seller.java
 * 
 * Aimy
 * 下午12:44:16
 */
package tk.codecube.test.aop.springcore.entry.impl;

import org.apache.commons.lang3.RandomUtils;

import tk.codecube.test.aop.springcore.entry.ISeller;

/**
 * @author Aimy
 * 2014年12月10日 下午12:44:16
 */
public class Seller implements ISeller{

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.entry.ISeller#sell(java.lang.String)
	 */
	@Override
	public int sell(String goods,String clientName) {
		System.out.println("Seller.sell()-->"+goods+" TO "+clientName);
		return RandomUtils.nextInt(0, 100);
	}

}
