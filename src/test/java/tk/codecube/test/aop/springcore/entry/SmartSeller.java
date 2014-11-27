/**
 * SmartSeller.java
 * 
 * Aimy
 * 上午10:09:48
 */
package tk.codecube.test.aop.springcore.entry;


/**
 * @author Aimy
 * 2014年11月27日 上午10:09:48
 */
public class SmartSeller extends Seller{
	
	/**
	 *  显示物品
	 * @auther Aimy
	 * 2014年11月27日 上午10:10:29
	 */
	protected void showGoods(String goods,String clientName)
	{
		System.out.println("SmartSeller.showGoods()--->"+goods+" TO "+clientName);
	}

}
