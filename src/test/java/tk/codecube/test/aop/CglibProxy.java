/**
 * CglibProxy.java
 * 
 * Aimy
 * 下午1:59:36
 */
package tk.codecube.test.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author Aimy
 * 2014年10月13日 下午1:59:36
 */
public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class<?> clazz)
	{
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/* (non-Javadoc)
	 * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("runing cglib start...");
		PerformanceMonitor.begin(obj.getClass().getName()+"."+method.getName());
		Object result = proxy.invokeSuper(obj, args);
		PerformanceMonitor.end();
		System.out.println("runing cglib end...");
		return result;
	}

}
