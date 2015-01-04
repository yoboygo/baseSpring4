/**
 * SmartSeller.java
 * 
 * Aimy
 * 上午10:09:48
 */
package tk.codecube.test.aop.springcore.entry.impl;

import org.apache.commons.lang3.RandomUtils;

import tk.codecube.test.aop.springcore.entry.ISeller;


/**
 * @author Aimy
 * 2014年11月27日 上午10:09:48
 */
public class SmartSeller implements ISeller{
	
	/**
	 *  显示物品
	 * @auther Aimy
	 * 2014年11月27日 上午10:10:29
	 */
	protected void showGoods(String goods)
	{
		System.out.println("SmartSeller.showGoods()--->"+goods);
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.entry.ISeller#sell(java.lang.String)
	 */
	@Override
	public int sell(String goods,String clientName) {
		System.out.println("SmartSeller.sell()--->"+goods+" TO "+clientName);
		return RandomUtils.nextInt(0, 100);
	}

}
