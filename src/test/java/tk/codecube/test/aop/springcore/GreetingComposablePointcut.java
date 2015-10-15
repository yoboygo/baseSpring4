/**
 * GreetingComposablePointcut.java
 * 
 * Aimy
 * 上午11:26:25
 */
package tk.codecube.test.aop.springcore;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

import tk.codecube.test.aop.springcore.entry.impl.WaiterDelegate;

/**
 * @author Aimy
 * 2014年11月13日 上午11:26:25
 */
public class GreetingComposablePointcut{

	public Pointcut getInstersectionPointcut()
	{
//		ComposablePointcut cp = new ComposablePointcut();
		Pointcut pt1 = new ControlFlowPointcut(WaiterDelegate.class,"service"); 
		
		NameMatchMethodPointcut pt2 = new NameMatchMethodPointcut();
		pt2.addMethodName("greetTo");
		return pt1;
/*		
  		return cp.intersection(pt1).intersection(new MethodMatcher() {
			
			@Override
			public boolean matches(Method method, Class<?> targetClass, Object[] args) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return "greetTo".equals(method.getName());
			}
			
			@Override
			public boolean isRuntime() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		*/
	}
}
