/**
 * NaughtyWaiter.java
 * 
 * Aimy
 * 上午10:30:15
 */
package tk.codecube.test.aop.springcore.entry;

import tk.codecube.test.aop.annotation.NeedTest;

/**
 * @author Aimy
 * 2014年11月27日 上午10:30:15
 */
public class NaughtyWaiter extends Waiter{
	
	/**
	 *  joke
	 * @auther Aimy
	 * @param clientName
	 * @param time
	 * 2014年11月27日 上午10:31:59
	 */
	@NeedTest
	public void joke(String clientName,int time)
	{
		System.out.println("NaughtyWaiter.joke()--->"+clientName+" ON "+time+" minutes.");
	}

}
