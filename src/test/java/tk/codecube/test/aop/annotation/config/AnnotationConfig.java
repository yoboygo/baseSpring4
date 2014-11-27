/**
 * AnnotationConfig.java
 * 
 * Aimy
 * 上午10:18:15
 */
package tk.codecube.test.aop.annotation.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Aimy
 * 2014年11月27日 上午10:18:15
 */
@Aspect
public class AnnotationConfig {

	@AfterReturning("@annotation(tk.codecube.test.aop.annotation.NeedTest)")
	public void needTestFun()
	{
		System.out.println("AnnotationConfig.needTestFun() executed!");
	}
}
