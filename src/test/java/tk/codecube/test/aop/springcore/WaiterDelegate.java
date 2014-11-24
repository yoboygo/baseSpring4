/**
 * WaiterDelegate.java
 * 
 * Aimy
 * 上午10:31:22
 */
package tk.codecube.test.aop.springcore;

/**
 * @author Aimy
 * 2014年11月13日 上午10:31:22
 */
public class WaiterDelegate {
	
	private Waiter waiter;
	
	
	public Waiter getWaiter() {
		return waiter;
	}


	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}


	public void service(String clientName)
	{
		waiter.greetTo(clientName);
		waiter.serviceTo(clientName);
	}
	
}
