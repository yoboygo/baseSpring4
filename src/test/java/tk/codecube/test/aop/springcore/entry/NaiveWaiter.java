/**
 * NaiveWaiter.java
 * 
 * Aimy
 * 下午2:11:38
 */
package tk.codecube.test.aop.springcore.entry;

/**
 * @author Aimy
 * 2014年11月25日 下午2:11:38
 */
public class NaiveWaiter{

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#greetTo(java.lang.String)
	 */
	public void greetTo(String clientName) {
		System.out.println("NaiveWaiter:Greet to "+clientName);
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#servleTo(java.lang.String)
	 */
	public void serveTo(String clientName) {
		System.out.println("NaiveWaiter:Serve to "+clientName);

	}
	
	public void smileTo(String clientName)
	{
		System.out.println("NaiveWaiter.smileTo()---->"+clientName);
	}

}
