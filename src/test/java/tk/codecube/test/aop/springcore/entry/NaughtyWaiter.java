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
public class NaughtyWaiter implements IWaiter{
	
	/**
	 *  joke
	 * @authorAimy
	 * @param clientName
	 * @param time
	 * 2014年11月27日 上午10:31:59
	 */
	public void joke(String clientName,int time)
	{
		System.out.println("NaughtyWaiter.joke()--->"+clientName+" ON "+time+" minutes.");
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.entry.IWaiter#greetTo(java.lang.String)
	 */
	@Override
	public void greetTo(String clientName) {
		System.out.println("NaughtyWaiter.greetTo()--->"+clientName);
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.entry.IWaiter#serviceTo(java.lang.String)
	 */
	@NeedTest
	@Override
	public void serviceTo(String clientName) {
		System.out.println("NaughtyWaiter.serviceTo()--->"+clientName);
	}

}
