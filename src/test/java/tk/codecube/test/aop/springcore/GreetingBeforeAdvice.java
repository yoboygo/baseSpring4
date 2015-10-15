/**
 * GreetingBeforeAdvice.java
 * 
 * Aimy
 * 下午4:41:51
 */
package tk.codecube.test.aop.springcore;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author Aimy
 * 2014年11月11日 下午4:41:51
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice{

	/* (non-Javadoc)
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		
		System.out.println(target.getClass().getName()+" : "+method.getName());
		String clientName = args[0].toString();
		System.out.println("How are you "+clientName+" !");
		
	}

}
