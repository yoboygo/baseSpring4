/**
 * NaiveWaiter.java
 * 
 * Aimy
 * 下午2:11:38
 */
package tk.codecube.test.aop.springcore.entry.impl;

import tk.codecube.test.aop.springcore.entry.IWaiter;

/**
 * @author Aimy
 * 2014年11月25日 下午2:11:38
 */
public class NaiveWaiter implements IWaiter{

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#greetTo(java.lang.String)
	 */
	public void greetTo(String clientName) {
		System.out.println("NaiveWaiter:Greet to "+clientName);
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#serviceTo(java.lang.String)
	 */
	public void serviceTo(String clientName) {
		System.out.println("NaiveWaiter:Serve to "+clientName);

	}
	
	public void smileTo(String clientName)
	{
		System.out.println("NaiveWaiter.smileTo()---->"+clientName);
	}

}
