/**
 * Waiter.java
 * 
 * Aimy
 * 下午4:25:05
 */
package tk.codecube.test.aop.springcore.entry.impl;

import tk.codecube.test.aop.springcore.entry.IWaiter;

/**
 * @author Aimy
 * 2014年11月11日 下午4:25:05
 */
public class Waiter implements IWaiter{

	public void greetTo(String name)
	{
		System.out.println("waiter greet to "+name);
	}
	
	public void serviceTo(String name)
	{
		System.out.println("waiter service to "+name);
	}

}
