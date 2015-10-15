/**
 * GreetingAdvisor.java
 * 
 * Aimy
 * 下午4:30:52
 */
package tk.codecube.test.aop.springcore;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import tk.codecube.test.aop.springcore.entry.impl.Waiter;

/**
 * @author Aimy
 * 2014年11月11日 下午4:30:52
 */
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut{

	public static List<String> specialClient = new ArrayList<String>();
	
	static{
		specialClient.add("John");
		specialClient.add("Tom");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, java.lang.Class, java.lang.Object[])
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass, Object[] args) {
		String name = args[0].toString();
		System.out.println("调用 matches(Method method, Class<?> targetClass, Object[] args)对"+targetClass.getSimpleName()+":"+method.getName()+"做动态检查!");
		return specialClient.contains(name);
	}
	public boolean matches(Method method, Class<?> targetClass) {
		
		System.out.println("调用 matches(Method method, Class<?> targetClass)对"+targetClass.getSimpleName()+":"+method.getName()+"做静态检查!");
		return "greetTo".endsWith(method.getName());
	}

	public ClassFilter getClassFilter()
	{
		return new ClassFilter() {
			
			@Override
			public boolean matches(Class<?> clazz) {
				System.out.println("调用 getClassFilter对"+clazz.getSimpleName()+"做静态检查!");
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}

}
