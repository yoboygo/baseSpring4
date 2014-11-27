/**
 * PreGreetingAspect.java
 * 
 * Aimy
 * 下午2:13:44
 */
package tk.codecube.test.aop.springcore;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Aimy
 * 2014年11月25日 下午2:13:44
 */
@Aspect
public class PreGreetingAspect {
	
	@Before("execution(* greetTo(..))")
	public void beforeGreeting()
	{
		System.out.println("How are you!");
	}

}
