package tk.codecube.test.aop.springcore.entry.impl;

import tk.codecube.test.aop.annotation.NeedTest;

public class SimpleWaiter extends NaiveWaiter {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#greetTo(java.lang.String)
	 */
	@NeedTest
	public void greetTo(String clientName) {
		System.out.println(name+":Greet to "+clientName);
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.springcore.IWaiter#serviceTo(java.lang.String)
	 */
	public void serviceTo(String clientName) {
		System.out.println(name+":Serve to "+clientName);

	}
	
	public void smileTo(String clientName,int minute)
	{
		System.out.println(name+".smileTo()---->"+clientName+" "+minute+"s");
	}

}
