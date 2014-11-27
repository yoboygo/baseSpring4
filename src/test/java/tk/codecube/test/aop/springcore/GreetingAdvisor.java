/**
 * GreetingAdvisor.java
 * 
 * Aimy
 * 下午4:30:52
 */
package tk.codecube.test.aop.springcore;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import tk.codecube.test.aop.springcore.entry.Waiter;

/**
 * @author Aimy
 * 2014年11月11日 下午4:30:52
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1650395746639360072L;

	/* (non-Javadoc)
	 * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, java.lang.Class)
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "greetTo".equals(method.getName());
	}

	public ClassFilter getClassFilter()
	{
		
		return new ClassFilter() {
			
			@Override
			public boolean matches(Class<?> clazz) {
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}

}
