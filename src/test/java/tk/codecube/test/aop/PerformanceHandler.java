/**
 * PerformanceHandler.java
 * 
 * Aimy
 * 下午1:28:01
 */
package tk.codecube.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Aimy
 * 2014年10月13日 下午1:28:01
 */
public class PerformanceHandler implements InvocationHandler {

	private Object target;
	
	public PerformanceHandler(Object target)
	{
		this.target = target;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("runing in proxy start...");
		PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());
		Object obj = method.invoke(target, args);
		PerformanceMonitor.end();
		System.out.println("runing in proxy end...");
		return obj;
	}


}
